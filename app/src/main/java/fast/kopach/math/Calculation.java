package fast.kopach.math;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import fast.kopach.math.games.VariablesInGame;

import static java.security.AccessController.getContext;

/**
 * Created by Vova on 20.09.2017.
 */

public class Calculation {
    public static int addCoin;
    public static int scoreBoundaryPoint;

    // private static final String TAG = "myLogs";

    public static int getScoreBoundaryPoint(int bestScore) {
//        startCalculation(bestScore);

        return scoreBoundaryPoint;
    }

//    public static void startCalculation(int bestScore) {
//
//        switch (PreferenceHelper.launchedGame) {
//            case 1:
//                if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][3]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][4], VariablesInGame.ARRAY_GAME_ADD_COIN[3]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][2]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][3], VariablesInGame.ARRAY_GAME_ADD_COIN[2]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][1]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][2], VariablesInGame.ARRAY_GAME_ADD_COIN[1]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][0]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][1], VariablesInGame.ARRAY_GAME_ADD_COIN[0]);
//
//                } else if (bestScore >= 0) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [0][0], 0);
//
//                }
//
//                break;
//
//            case 2:
//                if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][3]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][4], VariablesInGame.ARRAY_GAME_ADD_COIN[3]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][2]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][3], VariablesInGame.ARRAY_GAME_ADD_COIN[2]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][1]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][2], VariablesInGame.ARRAY_GAME_ADD_COIN[1]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][0]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][1], VariablesInGame.ARRAY_GAME_ADD_COIN[0]);
//
//                } else if (bestScore >= 0) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [1][0], 0);
//
//                }
//
//                break;
//
//            case 3:
//                if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][3]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][4], VariablesInGame.ARRAY_GAME_ADD_COIN[3]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][2]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][3], VariablesInGame.ARRAY_GAME_ADD_COIN[2]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][1]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][2], VariablesInGame.ARRAY_GAME_ADD_COIN[1]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][0]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][1], VariablesInGame.ARRAY_GAME_ADD_COIN[0]);
//
//                } else if (bestScore >= 0) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [2][0], 0);
//
//                }
//                break;
//
//            case 4:
//                if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][3]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][4], VariablesInGame.ARRAY_GAME_ADD_COIN[3]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][2]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][3], VariablesInGame.ARRAY_GAME_ADD_COIN[2]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][1]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][2], VariablesInGame.ARRAY_GAME_ADD_COIN[1]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][0]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][1], VariablesInGame.ARRAY_GAME_ADD_COIN[0]);
//
//                } else if (bestScore >= 0) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [3][0], 0);
//
//                }
//
//                break;
//
//            case 5:
//                if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][3]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][4], VariablesInGame.ARRAY_GAME_ADD_COIN[3]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][2]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][3], VariablesInGame.ARRAY_GAME_ADD_COIN[2]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][1]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][2], VariablesInGame.ARRAY_GAME_ADD_COIN[1]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][0]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][1], VariablesInGame.ARRAY_GAME_ADD_COIN[0]);
//
//                } else if (bestScore >= 0) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [4][0], 0);
//
//                }
//
//                break;
//
//            case 6:
//                if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][3]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][4], VariablesInGame.ARRAY_GAME_ADD_COIN[3]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][2]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][3], VariablesInGame.ARRAY_GAME_ADD_COIN[2]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][1]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][2], VariablesInGame.ARRAY_GAME_ADD_COIN[1]);
//
//                } else if (bestScore >= VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][0]) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][1], VariablesInGame.ARRAY_GAME_ADD_COIN[0]);
//
//                } else if (bestScore >= 0) {
//                    method2(VariablesInGame.ARRAY_GAME_SCORE_BOUNDARY_POINT [5][0], 0);
//
//                }
//
//                break;
//        }
//
//    }

//    private static void method2(int gameScoreBoundaryPoint, int gameAddCoin){
//        scoreBoundaryPoint = gameScoreBoundaryPoint;
//        addCoin = gameAddCoin;
//
//        calcCoin();
//    }

//    private static void calcCoin(){
//        if (PreferenceHelper.getUsingAddCoin(PreferenceHelper.launchedGame).contains(addCoin + "")) {
//        } else {
//            PreferenceHelper.setUsingAddCoin(PreferenceHelper.launchedGame, addCoin + "");
//            PreferenceHelper.setCoin(addCoin);
//        }
//    }
}
