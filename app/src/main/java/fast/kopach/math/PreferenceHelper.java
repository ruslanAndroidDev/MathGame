package fast.kopach.math;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import fast.kopach.math.games.VariablesInGame;
import fast.kopach.math.menu.MenuItemFragment;

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
    public static void setSound(boolean sound){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("sound", sound);

        Log.d(TAG, "Set Sound setting " + sound);

        editor.commit();
    }

    public static boolean isSoundOn(){
        return sharedPreferences.getBoolean("sound",true);
    }

    public static void firstCreateSharedPref(Context context) {
    }

    public static int getPrice(int position) {
        if (position == 4){
            return 1;
        }else if (position == 5) {
            return 2;
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

    public static int getCountReplayShow(){
        return sharedPreferences.getInt("count replay show", 0);
    }

    public static void setCountReplayShow(int add) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count replay show", getCountReplayShow() + add);
        editor.commit();
    }


    public static boolean getShowRatedGame(){
        return sharedPreferences.getBoolean("rated game", true);
    }

    public static void setShowDialogRatedGame(boolean ratedGame) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("rated game", ratedGame);
        editor.commit();
    }

    public static int getBoundaryForShowRate(){
        return sharedPreferences.getInt("boundary for show rate", VariablesInGame.BOUNDARY_FOR_SHOW_RATE);
    }

    public static void setLaterBoundaryForShowRate() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("boundary for show rate", getCountReplayShow() + 40);
        editor.commit();
    }
}
