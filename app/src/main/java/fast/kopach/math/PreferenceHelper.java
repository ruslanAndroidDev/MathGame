package fast.kopach.math;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Руслан on 17.09.2017.
 */

public class PreferenceHelper {
    static String PREFERENCE_NAME = "fastMathPref";
    static SharedPreferences sheredPreferences;

    public static int getBestScoreGame(int game, Context context) {
        if (sheredPreferences == null) {
            sheredPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sheredPreferences.getInt("bestScore" + game, 0);
    }

    public static void writeBestScoreGame(int game, int bestScore, Context context) {
        if (sheredPreferences == null) {
            sheredPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sheredPreferences.edit();
        editor.putInt("bestScore" + game, bestScore);
        editor.commit();
    }
}
