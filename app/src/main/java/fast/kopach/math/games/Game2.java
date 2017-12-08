package fast.kopach.math.games;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.customView.SquareButton;
import fast.kopach.math.dialogs.ReplayDialog;

public class Game2 extends AppCompatActivity {
    String taskStr = "";
    Random random;
    int score = 0;
    int bestScore;
    int true_answer;
    TextView tv2;
    Button[] buttonArray;
    Handler handler;
    Button variantbtn1, variantbtn2, variantbtn3, variantbtn4, variantbtn5, variantbtn6;
    private HeaderFragment headerFragment;
    ReplayDialog replayDialog;
    SquareButton errorClickedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        PreferenceHelper.launchedGame = 2;

        random = new Random();
        tv2 = findViewById(R.id.tv2);
        variantbtn1 = findViewById(R.id.btn2);
        variantbtn3 = findViewById(R.id.btn1);
        variantbtn2 = findViewById(R.id.btn3);
        variantbtn4 = findViewById(R.id.btn4);
        variantbtn5 = findViewById(R.id.btn5);
        variantbtn6 = findViewById(R.id.btn6);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        buttonArray = new Button[]{variantbtn1, variantbtn2, variantbtn3, variantbtn4, variantbtn5, variantbtn6};
        bestScore = PreferenceHelper.getBestScoreGame(2, this);
        headerFragment.setBestScore(bestScore);
        buildGame();
        handler = new Handler();
        replayDialog = new ReplayDialog(this);
    }

    private void buildGame() {
        if (errorClickedBtn != null) {
            errorClickedBtn.setBackground(getResources().getDrawable(R.drawable.game5btn_norm));
        }
        int propysk = random.nextInt(3);
        int number1 = random.nextInt(25) + random.nextInt((score + 1) * 5);
        int number2 = random.nextInt(25) + random.nextInt((score + 1) * 4);
        int znak = random.nextInt(2);
        switch (propysk) {
            case 0:  // пропуск в першого числа
                true_answer = number1;
                if (znak == 0) {
                    int result = number1 + number2;
                    taskStr = "<font color=#4000FF>? </font> <font color=#000000> + " + number2 + " = " + result + "</font>";
                } else {
                    int result = number1 - number2;
                    taskStr = "<font color=#4000FF>?</font> <font color=#000000> - " + number2 + " = " + result + "</font>";
                }
                break;

            case 1:
                true_answer = number2;
                if (znak == 0) {
                    int result = number1 + number2;
                    taskStr = "<font color=#000000>" + number1 + " +</font> <font color=#4000FF> ? </font>" + "<font color=#000000>= " + result + "</font>";
                } else {
                    int result = number1 - number2;
                    taskStr = "<font color=#000000>" + number1 + " -</font> <font color=#4000FF> ? </font>" + "<font color=#000000>= " + result + "</font>";
                }
                break;

            case 2:
                int result;
                if (znak == 0) {
                    result = number1 + number2;
                    taskStr = "<font color=#000000>" + number1 + " + " + number2 + " =</font> <font color=#4000FF> ? </font>";
                } else {
                    result = number1 - number2;
                    taskStr = "<font color=#000000>" + number1 + " - " + number2 + " =</font> <font color=#4000FF> ? </font>";
                }
                true_answer = result;
                break;
        }
        tv2.setText(Html.fromHtml(taskStr));
        headerFragment.startTimer(VariablesInGame.ARRAY_TIMER_IN_GAME[1], new HeaderFragment.TimerListener() {
            @Override
            public void onTimerFinish() {
                showDialog();
            }
        });
        fillVariants();
    }

    private void fillVariants() {
        for (int i = 0; i < 6; i++) {
            buttonArray[i].setText(random.nextInt(25) + random.nextInt((score + 1) * 5) + "");
        }
        int trueBtn = random.nextInt(6);
        buttonArray[trueBtn].setText(true_answer + "");
        checkIsUnique();
    }

    private void checkIsUnique() {
        for (int i = 0; i < 6; i++) {
            for (int j = 1 + i; j < buttonArray.length; j++) {
                if (buttonArray[i].getText().equals(buttonArray[j].getText())) {
                    buttonArray[i].setText(random.nextInt(25) + random.nextInt((score + 1) * 5) + "");
                    checkIsUnique();
                }
            }
        }
    }

    public void onClickGame2(final View view) {
        final View view1 = view;
        if (Integer.parseInt(((Button) view).getText().toString()) == true_answer) {
            score += 1;
            if (score > bestScore) {
                bestScore = score;
                PreferenceHelper.writeBestScoreGame(2, bestScore);
                headerFragment.setBestScore(bestScore);
            }
            headerFragment.setScore(score);
            ((SquareButton) view1).setBackground(getResources().getDrawable(R.drawable.game5btn_true));
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((SquareButton) view1).setBackground(getResources().getDrawable(R.drawable.game5btn_norm));
                    buildGame();
                }
            }, 500);
        } else {
            errorClickedBtn = (SquareButton) view;
            errorClickedBtn.setBackground(getResources().getDrawable(R.drawable.game5btn_error));
            showDialog();
        }
    }

    void showDialog() {
        headerFragment.stopTimer();
        replayDialog.show(getFragmentManager(), score, 2, score / 4, new ReplayDialog.ReplayListener() {
            @Override
            public void onReplayClick() {
                score = 0;
                headerFragment.setScore(score);
                replayDialog.dismiss();
                buildGame();
            }

            @Override
            public void onBackClick() {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        headerFragment.stopTimer();
    }
}
