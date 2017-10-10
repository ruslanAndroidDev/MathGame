package fast.kopach.math.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import fast.kopach.math.Calculation;
import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.menu.MenuActivity;

public class Game1 extends AppCompatActivity {
    TextView textView;
    String textPryklad = "";
    private int score = 0;
    private int bestScore;
    private int min_value;
    private int max_value;
    private Random random;
    private int result;
    String myAnswer = "";
    HeaderFragment headerFragment;
    Handler handler;
    ReplayDialog replayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        textView = (TextView) findViewById(R.id.textView);
        random = new Random();
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        bestScore = PreferenceHelper.getBestScoreGame(1);
        headerFragment.setBestScore(bestScore);
        handler = new Handler();
        replayDialog = new ReplayDialog(this);

        buildGame();

    }

    public void numberClick(View view) {
        TextView onClickedTv = (TextView) view;
        myAnswer += onClickedTv.getText().toString();
        textView.setText(textPryklad + myAnswer);
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.btn_ok) {
            checkAnswer();
        } else {
            try {
                myAnswer = myAnswer.substring(0, myAnswer.length() - 1);
                textView.setText(textPryklad + myAnswer);
            } catch (StringIndexOutOfBoundsException e) {
                textView.setText(textPryklad);
            }
        }
    }

    private void checkAnswer() {
        if (!myAnswer.equals("")) {
            if (Integer.parseInt(myAnswer) == result) {
                score += 1;
                if (score > bestScore) {
                    bestScore = score;
                    PreferenceHelper.writeBestScoreGame(1, bestScore);
                    headerFragment.setBestScore(bestScore);
                }
                headerFragment.setScore(score);
                buildGame();
            } else {

                replayDialog.show(getFragmentManager(), score, new ReplayDialog.ReplayListener() {
                    @Override
                    void onReplayClick() {
                        replayDialog.dismiss();
                        score = 0;
                        buildGame();
                        headerFragment.setScore(score);
                    }

                    @Override
                    void onBackClick() {
                        finish();
                    }
                });
            }
        } else {
            Toast.makeText(this, "Введіть відповідь!", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildGame() {
        int prykladrandom = (int) (Math.random() * 2);

        myAnswer = "";

        min_value = 20 + score * 15;
        max_value = 80 + score * 15;
        int number_1 = random.nextInt(max_value - min_value) + min_value;
        int number_2 = random.nextInt(max_value - min_value) + min_value;

        switch (prykladrandom) {
            case 0:
                result = number_1 + number_2;
                textPryklad = number_1 + "+" + number_2 + "=";
                break;

            case 1:
                result = number_1 - number_2;
                textPryklad = number_1 + "-" + number_2 + "=";
                break;
        }
        textView.setText(textPryklad);

       // Calculation.startCalculation(PreferenceHelper.getBestScoreGame(1));

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
