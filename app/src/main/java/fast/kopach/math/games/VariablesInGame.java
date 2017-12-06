package fast.kopach.math.games;

/**
 * Created by vova on 02.09.17.
 */

public class VariablesInGame {

    public static boolean isShowInterstitialAd = false;
    public static int SHOW_REPLAY_COUNT = 0;// Лічильник показу ріплей від запуску до завершення гри
    public static int [] boundary_point_show_replay_for_ad = {8, 17, 25, 36, 45, 50, 60, 70, 80, 90, 100};  // Точки межі, після яких буде показано міжсторінкову або відеорекламу

  /*  public static final int [][] ARRAY_GAME_SCORE_BOUNDARY_POINT = {
            {2, 3, 10, 80, 110},
            {20, 45, 70, 100, 125},
            {25, 50, 75, 100, 150},
            {8, 15, 25, 40, 65},
            {10, 25, 35, 55, 80},
            {15, 30, 50, 75, 100}};  */

   // public static final int [] ARRAY_GAME_ADD_COIN = {8, 12, 25, 35, 45};
    public static final int [] ARRAY_TIMER_IN_GAME = {15, 15, 15, 50, 30, 20};
    public static final long TIME_TO_BONUS_COINS = 60*60*24*8 * 1000; //Множиться на тисячу, так як час подається в мілісікундах
    public static final int BOUNDARY_FOR_SHOW_RATE = 50;


}
