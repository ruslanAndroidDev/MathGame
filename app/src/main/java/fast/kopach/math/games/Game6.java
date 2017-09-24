package fast.kopach.math.games;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;

public class Game6 extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5;
    Button[] buttons;
    int score;
    int bestScore;
    Random random;
    String pryklad;
    Button trueBtn;
    Handler handler;
    private HeaderFragment headerFragment;
    ReplayDialog replayDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);

        btn1 = (Button) findViewById(R.id.game6_btn1);
        btn2 = (Button) findViewById(R.id.game6_btn2);
        btn3 = (Button) findViewById(R.id.game6_btn3);
        btn4 = (Button) findViewById(R.id.game6_btn4);
        btn5 = (Button) findViewById(R.id.game6_btn5);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        bestScore = PreferenceHelper.getBestScoreGame(6, this);
        headerFragment.setBestScore(bestScore);
        buttons = new Button[]{btn1, btn2, btn3, btn4, btn5};
        random = new Random();
        handler = new Handler();
        replayDialog = new ReplayDialog(this, 6);
        buildGame();
    }

    public void game6Click(final View view) {
        if (((Button) view) == trueBtn) {
            score += 1;
            if (score > bestScore) {
                bestScore = score;
                PreferenceHelper.writeBestScoreGame(6, bestScore, this);
                headerFragment.setBestScore(bestScore);
            }
            headerFragment.setScore(score);
            view.setBackgroundColor(Color.GREEN);
            handler.postDelayed(new Runnable() {
                public void run() {
                    buildGame();
                    view.setBackgroundColor(Color.parseColor("#4370c2"));
                }
            }, 500);
        } else {
            view.setBackgroundColor(Color.RED);
            replayDialog.show(getFragmentManager(), score, bestScore, new ReplayDialog.ReplayListener() {
                @Override
                void onReplayClick() {
                    score = 0;
                    headerFragment.setScore(0);
                    buildGame();
                    view.setBackgroundColor(Color.parseColor("#4370c2"));
                    replayDialog.dismiss();
                }

                @Override
                void onBackClick() {
                    finish();
                }
            });
        }
    }

    private void buildGame() {
        fillAllVariantInFalse();
        setTrueVariant();
    }

    private void setTrueVariant() {
        int answerBtn = random.nextInt(5);
        int num1 = random.nextInt(50) + score * 3;
        int num2 = random.nextInt(50) + score * 3;
        int znak = random.nextInt(2);
        String trueAnswerStr;
        if (znak == 0) {
            int result = num1 + num2;
            trueAnswerStr = num1 + "+" + num2 + "=" + result;
        } else {
            int result = num1 - num2;
            trueAnswerStr = num1 + "-" + num2 + "=" + result;
        }
        buttons[answerBtn].setText(trueAnswerStr);
        trueBtn = buttons[answerBtn];
    }

    private void fillAllVariantInFalse() {
        for (int i = 0; i < 5; i++) {
            int num1 = random.nextInt(50) + score * 3;
            int num2 = random.nextInt(50) + score * 3;
            int znak = random.nextInt(2);
            int result = random.nextInt(150);
            if (znak == 0) {
                pryklad = num1 + "+" + num2 + "=" + result;
            } else {
                pryklad = num1 + "-" + num2 + "=-" + result;
            }
            buttons[i].setText(pryklad);
        }
    }
}
