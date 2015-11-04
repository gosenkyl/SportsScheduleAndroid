package com.sportsschedule.gosenk.sportsscheduleandroid.teams.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.ScheduleOnClick;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.Team;
import com.sportsschedule.gosenk.sportsscheduleandroid.teams.TeamHolder;

/**
 * Created by GosenK on 10/30/2015.
 */
public class NFLFragment extends Fragment {

    private static final int COLUMNS = 3;

    private View nflView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(nflView == null) {
            View view = inflater.inflate(R.layout.team_tab, container, false);
            TeamHolder teamHolder = TeamHolder.getInstance();
            loadNFLTeams(inflater, view, teamHolder);
            nflView = view;
        }

        return nflView;
    }


    private void loadNFLTeams(LayoutInflater inflater, View view, TeamHolder teamHolder){
        // Table of NFL teams
        TableRow row = null;
        TableLayout layout = (TableLayout) view.findViewById(R.id.team_list);

        int i = 0;
        int colCount = 0;
        for(Team team : teamHolder.getNflTeamList()){
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

            String uri = "@drawable/"+team.getLogoURL();
            int imageResource = getResources().getIdentifier(uri, null, view.getContext().getPackageName());

            Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageResource);
            Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, (int) (bMap.getWidth() * 0.5), (int) (bMap.getHeight() * 0.5), true);


            // Circle?
            Bitmap circleBitmap = Bitmap.createBitmap(bMapScaled.getWidth(), bMapScaled.getHeight(), Bitmap.Config.ARGB_8888);

            BitmapShader shader = new BitmapShader (bMapScaled,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            Paint paint = new Paint();
            paint.setShader(shader);
            paint.setAntiAlias(true);
            paint.setColor(getResources().getColor(R.color.shade1));

            Canvas c = new Canvas(circleBitmap);
            c.drawCircle(bMapScaled.getWidth() / 2, bMapScaled.getHeight() / 2, bMapScaled.getWidth() / 2, paint);

            ImageView circ = new ImageView(view.getContext());
            circ.setLayoutParams(params);
            circ.setImageBitmap(circleBitmap);

            ScheduleOnClick clickListener = new ScheduleOnClick(inflater, view, team);

            circ.setOnClickListener(clickListener);

            linear.addView(circ);


            // NFL Team Image
            /*ImageView img = new ImageView(view.getContext());
            img.setLayoutParams(params);
            img.setImageBitmap(bMapScaled);

            linear.addView(img);*/

            // NFL Team Name
            TextView text = new TextView(view.getContext());
            text.setLayoutParams(params);
            text.setText(team.getCity() + " \n " + team.getMascot());

            text.setTextColor(getResources().getColor(R.color.shade4));
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

}
