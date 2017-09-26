package fast.kopach.math;

import android.content.Context;

import fast.kopach.math.games.VariablesInGame;

/**
 * Created by Vova on 20.09.2017.
 */

public class Calculation {

    public static int addCoin;
    public static int scoreBoundaryPoint;

    public static int getScoreBoundaryPoint(int bestScore) {
        getInfoActiveGameAtTheMoment(bestScore);
        return scoreBoundaryPoint;
    }

    public static int getAddCoin(int bestScore) {
        getInfoActiveGameAtTheMoment(bestScore);
        return addCoin;
    }

    public static void getInfoActiveGameAtTheMoment(int bestScore){
        if (PreferenceHelper.launchedGame.equals("game 1")){
            if (bestScore > VariablesInGame.INT_game1FourthScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game1FifthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFifthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game1ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game1FourthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFourthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game1SecondScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game1ThirdScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameThirdAddCoin;

            }else if (bestScore > VariablesInGame.INT_game1FirstScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game1SecondScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameSecondAddCoin;

            }else if (bestScore > 0){
                scoreBoundaryPoint = VariablesInGame.INT_game1FirstScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFirstAddCoin;
            }


        }else if (PreferenceHelper.launchedGame.equals("game 2")) {
            if (bestScore > VariablesInGame.INT_game2FourthScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game2FifthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFifthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game2ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game2FourthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFourthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game2SecondScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game2ThirdScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameThirdAddCoin;

            }else if (bestScore > VariablesInGame.INT_game2FirstScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game2SecondScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameSecondAddCoin;

            }else if (bestScore > 0){
                scoreBoundaryPoint = VariablesInGame.INT_game2FirstScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFirstAddCoin;
            }


        }else if (PreferenceHelper.launchedGame.equals("game 3")) {
            if (bestScore > VariablesInGame.INT_game3FourthScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game3FifthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFifthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game3ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game3FourthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFourthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game3SecondScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game3ThirdScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameThirdAddCoin;

            }else if (bestScore > VariablesInGame.INT_game3FirstScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game3SecondScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameSecondAddCoin;

            }else if (bestScore > 0){
                scoreBoundaryPoint = VariablesInGame.INT_game3FirstScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFirstAddCoin;
            }


        }else if (PreferenceHelper.launchedGame.equals("game 4")) {
            if (bestScore > VariablesInGame.INT_game4FourthScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game4FifthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFifthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game4ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game4FourthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFourthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game4SecondScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game4ThirdScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameThirdAddCoin;

            }else if (bestScore > VariablesInGame.INT_game4FirstScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game4SecondScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameSecondAddCoin;

            }else if (bestScore > 0){
                scoreBoundaryPoint = VariablesInGame.INT_game4FirstScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFirstAddCoin;
            }


        }else if (PreferenceHelper.launchedGame.equals("game 5")) {
            if (bestScore > VariablesInGame.INT_game5FourthScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game5FifthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFifthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game5ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game5FourthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFourthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game5SecondScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game5ThirdScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameThirdAddCoin;

            }else if (bestScore > VariablesInGame.INT_game5FirstScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game5SecondScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameSecondAddCoin;

            }else if (bestScore > 0){
                scoreBoundaryPoint = VariablesInGame.INT_game5FirstScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFirstAddCoin;
            }


        }else if (PreferenceHelper.launchedGame.equals("game 6")) {
            if (bestScore > VariablesInGame.INT_game6FourthScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game6FifthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFifthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game6ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game6FourthScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFourthAddCoin;

            }else if (bestScore > VariablesInGame.INT_game6SecondScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game6ThirdScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameThirdAddCoin;

            }else if (bestScore > VariablesInGame.INT_game6FirstScoreBoundaryPoint){
                scoreBoundaryPoint = VariablesInGame.INT_game6SecondScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameSecondAddCoin;

            }else if (bestScore > 0){
                scoreBoundaryPoint = VariablesInGame.INT_game6FirstScoreBoundaryPoint;
                addCoin = VariablesInGame.INT_gameFirstAddCoin;
            }
        }
    }
}
