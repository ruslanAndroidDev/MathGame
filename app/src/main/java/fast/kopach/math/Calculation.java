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
        startCalculation(bestScore);

        return scoreBoundaryPoint;
    }

    public static void startCalculation(int bestScore) {

        switch (PreferenceHelper.launchedGame) {
            case 1:
                if (bestScore > VariablesInGame.INT_game1FourthScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game1FifthScoreBoundaryPoint, VariablesInGame.INT_gameFifthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game1ThirdScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game1FourthScoreBoundaryPoint, VariablesInGame.INT_gameFourthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game1SecondScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game1ThirdScoreBoundaryPoint, VariablesInGame.INT_gameThirdAddCoin);

                } else if (bestScore > VariablesInGame.INT_game1FirstScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game1SecondScoreBoundaryPoint, VariablesInGame.INT_gameSecondAddCoin);

                } else if (bestScore >= 0) {
                    method2(VariablesInGame.INT_game1FirstScoreBoundaryPoint, VariablesInGame.INT_gameFirstAddCoin);

                }

                break;

            case 2:
                if (bestScore > VariablesInGame.INT_game2FourthScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game2FifthScoreBoundaryPoint, VariablesInGame.INT_gameFifthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game2ThirdScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game2FourthScoreBoundaryPoint, VariablesInGame.INT_gameFourthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game2SecondScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game2ThirdScoreBoundaryPoint, VariablesInGame.INT_gameThirdAddCoin);

                } else if (bestScore > VariablesInGame.INT_game2FirstScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game2SecondScoreBoundaryPoint, VariablesInGame.INT_gameSecondAddCoin);

                } else if (bestScore >= 0) {
                    method2(VariablesInGame.INT_game2FirstScoreBoundaryPoint, VariablesInGame.INT_gameFirstAddCoin);

                }

                break;

            case 3:
                if (bestScore > VariablesInGame.INT_game3FourthScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game3FifthScoreBoundaryPoint, VariablesInGame.INT_gameFifthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game3ThirdScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game3FourthScoreBoundaryPoint, VariablesInGame.INT_gameFourthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game3SecondScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game3ThirdScoreBoundaryPoint, VariablesInGame.INT_gameThirdAddCoin);

                } else if (bestScore > VariablesInGame.INT_game3FirstScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game3SecondScoreBoundaryPoint, VariablesInGame.INT_gameSecondAddCoin);

                } else if (bestScore >= 0) {
                    method2(VariablesInGame.INT_game3FirstScoreBoundaryPoint, VariablesInGame.INT_gameFirstAddCoin);

                }
                break;

            case 4:
                if (bestScore > VariablesInGame.INT_game4FourthScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game4FifthScoreBoundaryPoint, VariablesInGame.INT_gameFifthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game4ThirdScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game4FourthScoreBoundaryPoint, VariablesInGame.INT_gameFourthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game4SecondScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game4ThirdScoreBoundaryPoint, VariablesInGame.INT_gameThirdAddCoin);

                } else if (bestScore > VariablesInGame.INT_game4FirstScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game4SecondScoreBoundaryPoint, VariablesInGame.INT_gameSecondAddCoin);

                } else if (bestScore >= 0) {
                    method2(VariablesInGame.INT_game4FirstScoreBoundaryPoint, VariablesInGame.INT_gameFirstAddCoin);

                }

                break;

            case 5:
                if (bestScore > VariablesInGame.INT_game5FourthScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game5FifthScoreBoundaryPoint, VariablesInGame.INT_gameFifthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game5ThirdScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game5FourthScoreBoundaryPoint, VariablesInGame.INT_gameFourthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game5SecondScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game5ThirdScoreBoundaryPoint, VariablesInGame.INT_gameThirdAddCoin);

                } else if (bestScore > VariablesInGame.INT_game5FirstScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game5SecondScoreBoundaryPoint, VariablesInGame.INT_gameSecondAddCoin);

                } else if (bestScore >= 0) {
                    method2(VariablesInGame.INT_game5FirstScoreBoundaryPoint, VariablesInGame.INT_gameFirstAddCoin);

                }

                break;

            case 6:
                if (bestScore > VariablesInGame.INT_game6FourthScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game6FifthScoreBoundaryPoint, VariablesInGame.INT_gameFifthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game6ThirdScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game6FourthScoreBoundaryPoint, VariablesInGame.INT_gameFourthAddCoin);

                } else if (bestScore > VariablesInGame.INT_game6SecondScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game6ThirdScoreBoundaryPoint, VariablesInGame.INT_gameThirdAddCoin);

                } else if (bestScore > VariablesInGame.INT_game6FirstScoreBoundaryPoint) {
                    method2(VariablesInGame.INT_game6SecondScoreBoundaryPoint, VariablesInGame.INT_gameSecondAddCoin);

                } else if (bestScore >= 0) {
                    method2(VariablesInGame.INT_game6FirstScoreBoundaryPoint, VariablesInGame.INT_gameFirstAddCoin);

                }

                break;
        }

    }

    private static void method2(int INT_gameScoreBoundaryPoint, int INT_gameAddCoin){
        scoreBoundaryPoint = INT_gameScoreBoundaryPoint;
        addCoin = INT_gameAddCoin;

        coin();
    }

    private static void coin(){
        if (PreferenceHelper.getUsingAddCoin(PreferenceHelper.launchedGame).contains(addCoin + "")) {
        } else {
            PreferenceHelper.setUsingAddCoin(PreferenceHelper.launchedGame, addCoin + "");
            PreferenceHelper.setCoin(addCoin);
        }
    }
}
