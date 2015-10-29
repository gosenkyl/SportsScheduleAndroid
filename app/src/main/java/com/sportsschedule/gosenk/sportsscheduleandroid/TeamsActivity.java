package com.sportsschedule.gosenk.sportsscheduleandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TeamsActivity extends AppCompatActivity {

    private static final int COLUMNS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        TeamHolder teamHolder = TeamHolder.getInstance(null);

        TableRow row = null;
        TableLayout layout = (TableLayout) findViewById(R.id.team_list);

        int i = 0;
        int colCount = 0;
        for(Team team : teamHolder.getNflTeamList()){
            if(i % 3 == 0){
                colCount = 0;
                row = new TableRow(this);
                layout.addView(row);
            }

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            LinearLayout linear = new LinearLayout(this);
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(lp);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            String uri = "@drawable/"+team.getLogoURL();
            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

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

            ImageView circ = new ImageView(this);
            circ.setLayoutParams(params);
            circ.setImageBitmap(circleBitmap);

            linear.addView(circ);

            // NFL Team Image
            ImageView img = new ImageView(this);
            img.setLayoutParams(params);
            img.setImageBitmap(bMapScaled);

            //linear.addView(img);

            // NFL Team Name
            TextView text = new TextView(this);
            text.setLayoutParams(params);
            text.setText(team.getCity() + " \n " + team.getMascot());

            text.setTextColor(getResources().getColor(R.color.shade4));
            text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
            text.setTextAppearance(this, R.style.font_thin);
            text.setGravity(Gravity.CENTER);

            linear.addView(text);

            row.addView(linear);

            i++;
            colCount++;
        }

        for (int j = COLUMNS - colCount; j > 0; j--) {
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            LinearLayout linear = new LinearLayout(this);
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setLayoutParams(lp);

            row.addView(linear);
        }

    }
}
