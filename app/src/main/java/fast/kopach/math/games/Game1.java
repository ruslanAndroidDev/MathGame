package fast.kopach.math.games;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.dialogs.ReplayDialog;

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
        bestScore = PreferenceHelper.getBestScoreGame(1, this);
        headerFragment.setBestScore(bestScore);
        handler = new Handler();
        replayDialog = new ReplayDialog(this);

        buildGame();

    }

    public void numberClick(View view) {
        TextView onClickedTv = (TextView) view;
        if (((TextView) view).getText().toString().equals("-")) {
            if (!myAnswer.contains("-")) {
                myAnswer += onClickedTv.getText().toString();
            }
        } else {
            myAnswer += onClickedTv.getText().toString();
        }
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
        if (!myAnswer.equals("") & !myAnswer.equals("-")) {
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
                headerFragment.stopTimer();
                showDialog();
            }
        } else {
            Snackbar snackbar = Snackbar.make(textView, "Please, enter your answer.", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            snackbar.show();
        }
    }

    void showDialog() {
        replayDialog.show(getFragmentManager(), score, new ReplayDialog.ReplayListener() {
            @Override
            public void onReplayClick() {
                replayDialog.dismiss();
                score = 0;
                buildGame();
                headerFragment.setScore(score);
            }

            @Override
            public void onBackClick() {
                finish();
            }
        });
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
        headerFragment.startTimer(VariablesInGame.ARRAY_TIMER_IN_GAME[0], new HeaderFragment.TimerListener() {

            @Override
            public void onTimerFinish() {
                showDialog();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        headerFragment.stopTimer();
    }
}
