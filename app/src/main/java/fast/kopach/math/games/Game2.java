package fast.kopach.math.games;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.customView.SquareButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        random = new Random();
        tv2 = (TextView) findViewById(R.id.tv2);
        variantbtn1 = (Button) findViewById(R.id.btn2);
        variantbtn3 = (Button) findViewById(R.id.btn1);
        variantbtn2 = (Button) findViewById(R.id.btn3);
        variantbtn4 = (Button) findViewById(R.id.btn4);
        variantbtn5 = (Button) findViewById(R.id.btn5);
        variantbtn6 = (Button) findViewById(R.id.btn6);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        buttonArray = new Button[]{variantbtn1, variantbtn2, variantbtn3, variantbtn4, variantbtn5, variantbtn6};
        bestScore = PreferenceHelper.getBestScoreGame(2, this);
        headerFragment.setBestScore(bestScore);
        buildGame();
        handler = new Handler();
        replayDialog = new ReplayDialog(this);
    }

    private void buildGame() {
        int propysk = 0;
        int number1 = random.nextInt(25) + score * 5;
        int number2 = random.nextInt(25) + score * 3;
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
                break;

            case 2:
//                true_answer = result;
                break;
        }
        tv2.setText(Html.fromHtml(taskStr));
        fillVariants();
    }

    private void fillVariants() {
        for (int i = 0; i < 6; i++) {
            buttonArray[i].setText(random.nextInt(50) + "");
        }

        int trueBtn = random.nextInt(6);
        buttonArray[trueBtn].setText(true_answer + "");
    }

    public void onClickGame2(final View view) {
        final View view1 = view;
        if (Integer.parseInt(((Button) view).getText().toString()) == true_answer) {
            ((SquareButton) view).setColor(Color.GREEN);
            score += 1;
            if (score > bestScore) {
                bestScore = score;
                PreferenceHelper.writeBestScoreGame(2, bestScore, this);
                headerFragment.setBestScore(bestScore);
            }
            headerFragment.setScore(score);
            handler.postDelayed(new Runnable() {
                public void run() {
                    ((SquareButton) view1).setColor(ContextCompat.getColor(Game2.this, R.color.game2Btn));
                    buildGame();
                }
            }, 500);
        } else {
            ((SquareButton) view).setColor(Color.RED);
            replayDialog.show(getFragmentManager(),bestScore, score, new ReplayDialog.ReplayListener() {
                @Override
                void onReplayClick() {
                    score = 0;
                    headerFragment.setScore(score);
                    replayDialog.dismiss();
                    buildGame();
                    ((SquareButton) view).setColor(Color.parseColor("#4775ba"));
                }

                @Override
                void onBackClick() {
                    finish();
                }
            });
        }
    }
}
