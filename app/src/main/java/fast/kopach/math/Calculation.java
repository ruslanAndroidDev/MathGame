package fast.kopach.math;

import android.content.Context;
import android.util.Log;

import fast.kopach.math.games.PointsInGame;

/**
 * Created by Vova on 20.09.2017.
 */

public class Calculation {

 /*   public static int addCoin;
    public static float scoreBoundaryPoint;

    public static int getBestScore(){
        int bestScore;
        Context context = null;
        if (PreferenceHelper.getLaunchedGame().equals("game 6")){
            bestScore = PreferenceHelper.getBestScoreGame(6, context);
        }else if (PreferenceHelper.getLaunchedGame().equals("game 5")){
            bestScore = PreferenceHelper.getBestScoreGame(5, context);
        }else if (PreferenceHelper.getLaunchedGame().equals("game 4")){
            bestScore = PreferenceHelper.getBestScoreGame(4, context);
        }else if (PreferenceHelper.getLaunchedGame().equals("game 3")){
            bestScore = PreferenceHelper.getBestScoreGame(3, context);
        }else if (PreferenceHelper.getLaunchedGame().equals("game 2")){
            bestScore = PreferenceHelper.getBestScoreGame(2, context);
        }else {
            bestScore = PreferenceHelper.getBestScoreGame(1, context);
        }

        return bestScore;
    }

    public static void getInfoActiveGameAtTheMoment(int bestScore){
        if (PreferenceHelper.getLaunchedGame().equals("game 1")){
            if (bestScore > PointsInGame.INT_game1FourthScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game1FifthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game1FifthAddCoin;

            }if (bestScore > PointsInGame.INT_game1ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game1FourthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game1FourthAddCoin;

            }if (bestScore > PointsInGame.INT_game1SecondScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game1ThirdScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game1ThirdAddCoin;

            }if (bestScore > PointsInGame.INT_game1FirstScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game1SecondScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game1SecondAddCoin;

            }if (bestScore > 0){
                scoreBoundaryPoint = PointsInGame.INT_game1FirstScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game1FirstAddCoin;
            }


        } if (PreferenceHelper.getLaunchedGame().equals("game 2")) {
            if (bestScore > PointsInGame.INT_game2FourthScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game2FifthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game2FifthAddCoin;

            }if (bestScore > PointsInGame.INT_game2ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game2FourthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game2FourthAddCoin;

            }if (bestScore > PointsInGame.INT_game2SecondScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game2ThirdScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game2ThirdAddCoin;

            }if (bestScore > PointsInGame.INT_game2FirstScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game2SecondScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game2SecondAddCoin;

            }if (bestScore > 0){
                scoreBoundaryPoint = PointsInGame.INT_game2FirstScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game2FirstAddCoin;
            }


        }if (PreferenceHelper.getLaunchedGame().equals("game 3")) {
            if (bestScore > PointsInGame.INT_game3FourthScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game3FifthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game3FifthAddCoin;

            }if (bestScore > PointsInGame.INT_game3ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game3FourthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game3FourthAddCoin;

            }if (bestScore > PointsInGame.INT_game3SecondScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game3ThirdScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game3ThirdAddCoin;

            }if (bestScore > PointsInGame.INT_game3FirstScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game3SecondScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game3SecondAddCoin;

            }if (bestScore > 0){
                scoreBoundaryPoint = PointsInGame.INT_game3FirstScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game3FirstAddCoin;
            }


        }if (PreferenceHelper.getLaunchedGame().equals("game 4")) {
            if (bestScore > PointsInGame.INT_game4FourthScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game4FifthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game4FifthAddCoin;

            }if (bestScore > PointsInGame.INT_game4ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game4FourthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game4FourthAddCoin;

            }if (bestScore > PointsInGame.INT_game4SecondScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game4ThirdScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game4ThirdAddCoin;

            }if (bestScore > PointsInGame.INT_game4FirstScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game4SecondScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game4SecondAddCoin;

            }if (bestScore > 0){
                scoreBoundaryPoint = PointsInGame.INT_game4FirstScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game4FirstAddCoin;
            }


        }if (PreferenceHelper.getLaunchedGame().equals("game 5")) {
            if (bestScore > PointsInGame.INT_game5FourthScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game5FifthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game5FifthAddCoin;

            }if (bestScore > PointsInGame.INT_game5ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game5FourthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game5FourthAddCoin;

            }if (bestScore > PointsInGame.INT_game5SecondScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game5ThirdScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game5ThirdAddCoin;

            }if (bestScore > PointsInGame.INT_game5FirstScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game5SecondScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game5SecondAddCoin;

            }if (bestScore > 0){
                scoreBoundaryPoint = PointsInGame.INT_game5FirstScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game5FirstAddCoin;
            }


        }if (PreferenceHelper.getLaunchedGame().equals("game 6")) {
            if (bestScore > PointsInGame.INT_game6FourthScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game6FifthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game6FifthAddCoin;

            }if (bestScore > PointsInGame.INT_game6ThirdScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game6FourthScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game6FourthAddCoin;

            }if (bestScore > PointsInGame.INT_game6SecondScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game6ThirdScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game6ThirdAddCoin;

            }if (bestScore > PointsInGame.INT_game6FirstScoreBoundaryPoint){
                scoreBoundaryPoint = PointsInGame.INT_game6SecondScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game6SecondAddCoin;

            }if (bestScore > 0){
                scoreBoundaryPoint = PointsInGame.INT_game6FirstScoreBoundaryPoint;
                addCoin = PointsInGame.INT_game6FirstAddCoin;
            }
        }
    } */
}
