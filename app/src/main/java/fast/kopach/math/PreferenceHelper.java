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

    public static int launchedGame;

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


    public static void setCoin(int addCoin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("coin", getCoin() + addCoin);

        Log.d(TAG, "Додано монет " + addCoin);

        editor.commit();
    }

    public static int getCoin(){
        return sharedPreferences.getInt("coin", 0);
    }

    public static void firstCreateSharedPref(Context context){
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
    }

    public static int getPrice(int position) {
        if (position==5){
            return 100;
        }else if(position==6){
            return 200;
        }
        return 0;
    }
}
