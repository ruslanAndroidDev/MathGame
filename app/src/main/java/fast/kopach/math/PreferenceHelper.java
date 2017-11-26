package fast.kopach.math;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Руслан on 17.09.2017.
 */

public class PreferenceHelper {
    static String PREFERENCE_NAME = "fastMathPref";
    static SharedPreferences sharedPreferences;

    private static final String TAG = "myLogs";


    public static void setUsingAddCoin(int game, String involvedAddCoin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usingAddCoinInGame" + game, getUsingAddCoin(game) + involvedAddCoin);
        editor.commit();
    }

    public static String getUsingAddCoin(int game) {
        return sharedPreferences.getString("usingAddCoinInGame" + game, "");
    }

    public static void writeBestScoreGame(int game, int bestScore) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("bestScore" + game, bestScore);
        editor.commit();
    }

    public static int getBestScoreGame(int game, Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt("bestScore" + game, 0);
    }

    public static boolean isOpenGame(int game) {
        return sharedPreferences.getBoolean("game" + game, false);
    }

    public static void openGame(int game) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("game" + game, true);
        editor.commit();
    }


    public static void addCoin(int addCoin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("coin", getCoin() + addCoin);

        Log.d(TAG, "Додано монет " + addCoin);

        editor.commit();
    }

    public static void setCoin(int coin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("coin", coin);

        Log.d(TAG, "Встановлено монет" + coin);

        editor.commit();
    }

    public static int getCoin() {
        return sharedPreferences.getInt("coin", 0);
    }

    public static void firstCreateSharedPref(Context context) {
    }

    public static int getPrice(int position) {
        if (position == 5) {
            return 100;
        } else if (position == 6) {
            return 200;
        }
        return 0;
    }

    public static void launchGame(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("launche", getLauncheCount() + 1);
    }

    public static int getLauncheCount() {
        return sharedPreferences.getInt("launche", 0);
    }
}
