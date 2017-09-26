package fast.kopach.math.games;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.customView.SquareButton;

public class Game4 extends AppCompatActivity {
    SquareButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16,
            btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24;
    SquareButton[] buttonsArray;
    //максимальне число
    private int maxValue = 24;
    ArrayList<Integer> values;
    private HeaderFragment headerFragment;
    private int num_of_btn = 24;
    ArrayList<Integer> valuesForCheck;
    Handler handler;
    private int score = 0;
    private int bestScore;
    ReplayDialog replayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        bestScore = PreferenceHelper.getBestScoreGame(4, this);
        headerFragment.setScore(score);
        headerFragment.setBestScore(bestScore);

        btn1 = (SquareButton) findViewById(R.id.game4_btn1);
        btn2 = (SquareButton) findViewById(R.id.game4_btn2);
        btn3 = (SquareButton) findViewById(R.id.game4_btn3);
        btn4 = (SquareButton) findViewById(R.id.game4_btn4);
        btn5 = (SquareButton) findViewById(R.id.game4_btn5);
        btn6 = (SquareButton) findViewById(R.id.game4_btn6);
        btn7 = (SquareButton) findViewById(R.id.game4_btn7);
        btn8 = (SquareButton) findViewById(R.id.game4_btn8);
        btn9 = (SquareButton) findViewById(R.id.game4_btn9);
        btn10 = (SquareButton) findViewById(R.id.game4_btn10);
        btn11 = (SquareButton) findViewById(R.id.game4_btn11);
        btn12 = (SquareButton) findViewById(R.id.game4_btn12);
        btn13 = (SquareButton) findViewById(R.id.game4_btn13);
        btn14 = (SquareButton) findViewById(R.id.game4_btn14);
        btn15 = (SquareButton) findViewById(R.id.game4_btn15);
        btn16 = (SquareButton) findViewById(R.id.game4_btn16);
        btn17 = (SquareButton) findViewById(R.id.game4_btn17);
        btn18 = (SquareButton) findViewById(R.id.game4_btn18);
        btn19 = (SquareButton) findViewById(R.id.game4_btn19);
        btn20 = (SquareButton) findViewById(R.id.game4_btn20);
        btn21 = (SquareButton) findViewById(R.id.game4_btn21);
        btn22 = (SquareButton) findViewById(R.id.game4_btn22);
        btn23 = (SquareButton) findViewById(R.id.game4_btn23);
        btn24 = (SquareButton) findViewById(R.id.game4_btn24);

        handler = new Handler();

        buttonsArray = new SquareButton[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11,
                btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24};
        values = new ArrayList<>();
        valuesForCheck = new ArrayList<>();
        replayDialog = new ReplayDialog(this);
        buildGame();
    }

    private void buildGame() {
        generateValue();
        fillButton();
    }

    private void fillButton() {
        for (int i = 0; i < 24; i++) {
            buttonsArray[i].setText(values.get(i) + "");
        }
    }

    private void checkIsTrueAnswer(final SquareButton clickedBtn) {
        if (valuesForCheck.get(0) == Integer.parseInt(clickedBtn.getText().toString())) {
            valuesForCheck.remove(0);
            clickedBtn.setSelected(true);
            clickedBtn.setClickable(false);
            Log.d("tag", "size" + valuesForCheck.size());
            if (valuesForCheck.size() == 0) {
                score += 1;
                if (score > bestScore) {
                    bestScore = score;
                    PreferenceHelper.writeBestScoreGame(4, bestScore, this);
                    headerFragment.setBestScore(bestScore);
                }
                headerFragment.setScore(score);
                paintAllButtonGreen();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buildGame();
                        paintAllButtonStandart();
                    }
                }, 1000);
            }
        } else {
            clickedBtn.setColor(Color.RED);
            replayDialog.show(getFragmentManager(),bestScore, score, new ReplayDialog.ReplayListener() {
                @Override
                void onReplayClick() {
                    score = 0;
                    headerFragment.setScore(score);
                    buildGame();
                    paintAllButtonStandart();
                    replayDialog.dismiss();
                }

                @Override
                void onBackClick() {
                    finish();
                }
            });
        }
    }

    public void paintAllButtonGreen() {
        for (int i = 0; i < num_of_btn; i++) {
            buttonsArray[i].setSelected(false);
            buttonsArray[i].setColor(Color.GREEN);
        }
    }

    public void paintAllButtonStandart() {
        for (int i = 0; i < num_of_btn; i++) {
            buttonsArray[i].setClickable(true);
            buttonsArray[i].setSelected(false);
            buttonsArray[i].setColor(Color.parseColor("#4775ba"));
        }
    }

    public void onClick(View view) {
        checkIsTrueAnswer((SquareButton) view);
    }

    public void generateValue() {
        // заповнюємо масив числами
        values.clear();
        maxValue = 24 + score * 5;
        for (int i = 0; i < num_of_btn; i++) {
            values.add(new Random().nextInt(maxValue));
        }
        checkIsUnique();
        valuesForCheck.clear();
        for (int i = 0; i < num_of_btn; i++) {
            valuesForCheck.add(values.get(i));
        }
        //сортуємо від меншого до більшого
        for (int i = 0; i < valuesForCheck.size(); i++) {
            for (int j = 0; j < valuesForCheck.size(); j++) {
                if (valuesForCheck.get(i) < valuesForCheck.get(j)) {
                    int temp = valuesForCheck.get(i);
                    valuesForCheck.set(i, valuesForCheck.get(j));
                    valuesForCheck.set(j, temp);
                }
            }
        }
    }

    private void checkIsUnique() {
        for (int i = 0; i < values.size(); i++) {
            for (int j = 1 + i; j < values.size(); j++) {
                if (values.get(i) == values.get(j)) {
                    values.set(i, new Random().nextInt(maxValue));
                    checkIsUnique();
                }
            }
        }
    }
}
