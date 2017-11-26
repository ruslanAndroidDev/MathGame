package fast.kopach.math;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;

import fast.kopach.math.menu.MenuItemFragment;

/**
 * Created by Руслан on 11.09.2017.
 */

public class Utill {
    static Bitmap bitmap;

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    static MediaPlayer mp;

    public static Bitmap loadBitmapFromResource(Resources res, int resId,
                                                int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeResource(res, resId, options);

        return bitmap;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static void playSound(Context context) {
        if (mp == null) {
            mp = MediaPlayer.create(context, R.raw.click_sound);
        }
        mp.start();
    }



    public static String getGameName(int game){
        String gameName = "";
        switch (game) {
            case 1:
                gameName = "I'm a calculator";
                break;
            case 2:
                gameName = "Something is missing";
                break;
            case 3:
                gameName = "More or less";
                break;
            case 4:
                gameName = "In order";
                break;
            case 5:
                gameName = "Puzzle";
                break;
            case 6:
                gameName =  "Detective";
                break;
        }

        return gameName;
    }
}
