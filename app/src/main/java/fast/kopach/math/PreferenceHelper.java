package fast.kopach.math;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Руслан on 17.09.2017.
 */

public class PreferenceHelper {
    static String PREFERENCE_NAME = "fastMathPref";
    static SharedPreferences sp;

    public static int getBestScoreGame(int game, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sp.getInt("bestScore" + game, 0);
    }

    public static void writeBestScoreGame(int game, int bestScore, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("bestScore" + game, bestScore);
        editor.commit();
    }
}
