package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.schedule.ScheduleActivity;

import com.sportsschedule.gosenk.sportsscheduleandroid.dto.Team;

import java.util.List;

public final class TeamHelper {

    private static final int COLUMNS = 3;

    public static void loadTeams(View view, List<Team> teamList){

        // Table of teams
        TableRow row = null;
        TableLayout layout = (TableLayout) view.findViewById(R.id.team_list);

        int i = 0;
        int colCount = 0;
        for(final Team team : teamList){
            if(i % 3 == 0){
                colCount = 0;
                row = new TableRow(view.getContext());
                layout.addView(row);
            }

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            LinearLayout linear = new LinearLayout(view.getContext());
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(lp);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            Bitmap img = TeamHelper.getTeamLogoCircle(view.getContext(), team.getId().replace("-", "_").toLowerCase(), 0.5f);

            ImageView circ = new ImageView(view.getContext());
            circ.setLayoutParams(params);
            circ.setImageBitmap(img);

            circ.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent scheduleIntent = new Intent(v.getContext(), ScheduleActivity.class);
                    scheduleIntent.putExtra("team", team);
                    v.getContext().startActivity(scheduleIntent);
                }

            });

            linear.addView(circ);

            // NFL Team Name
            TextView text = new TextView(view.getContext());
            text.setLayoutParams(params);
            text.setText(team.getCity() + " \n " + team.getMascot());

            text.setTextColor(view.getResources().getColor(R.color.colorPrimaryDark));
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            text.setTextAppearance(view.getContext(), R.style.font_thin_bold);
            text.setGravity(Gravity.CENTER);

            linear.addView(text);

            row.addView(linear);

            i++;
            colCount++;
        }

        for (int j = COLUMNS - colCount; j > 0; j--) {
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            LinearLayout linear = new LinearLayout(view.getContext());
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(lp);

            row.addView(linear);
        }

    }

    public static Bitmap getTeamLogoCircle(Context context, String logoURL, float percent){

        String uri = "@drawable/"+logoURL;
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), imageResource);
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, (int) (bMap.getWidth() * percent), (int) (bMap.getHeight() * percent), true);

        // Circle?
        Bitmap circleBitmap = Bitmap.createBitmap(bMapScaled.getWidth(), bMapScaled.getHeight(), Bitmap.Config.ARGB_8888);

        BitmapShader shader = new BitmapShader (bMapScaled,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Paint paint = new Paint();
        paint.setShader(shader);
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));

        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bMapScaled.getWidth() / 2, bMapScaled.getHeight() / 2, bMapScaled.getWidth() / 2, paint);

        return circleBitmap;

    }

}
