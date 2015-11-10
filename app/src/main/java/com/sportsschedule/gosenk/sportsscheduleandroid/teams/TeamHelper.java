package com.sportsschedule.gosenk.sportsscheduleandroid.teams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.sportsschedule.gosenk.sportsscheduleandroid.R;

public final class TeamHelper {

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
        paint.setColor(context.getResources().getColor(R.color.shade1));

        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bMapScaled.getWidth() / 2, bMapScaled.getHeight() / 2, bMapScaled.getWidth() / 2, paint);

        return circleBitmap;

    }

}
