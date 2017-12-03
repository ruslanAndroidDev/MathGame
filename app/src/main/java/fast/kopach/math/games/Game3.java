package fast.kopach.math.games;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.customView.SquareButton;
import fast.kopach.math.dialogs.ReplayDialog;

public class Game3 extends AppCompatActivity {

    private HeaderFragment headerFragment;
    TextView example_left;
    TextView example_right;
    TextView mark_right;
    TextView mark_left;
    int sum_right;
    int sum_left;
    Random random;
    int num1left;
    int num2left;
    int mark;
    private int myScore = 0;
    int bestScore;
    ReplayDialog replayDialog;
    SquareButton errorClickedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        random = new Random();
        example_left = (TextView) findViewById(R.id.example_left);
        example_right = (TextView) findViewById(R.id.example_right);
        mark_right = (TextView) findViewById(R.id.tv_znak_right);
        mark_left = (TextView) findViewById(R.id.tv_znak_left);
        bestScore = PreferenceHelper.getBestScoreGame(3,this);
        headerFragment.setBestScore(bestScore);
        replayDialog = new ReplayDialog(this);

        buildGame();
    }

    private void buildGame() {
        if (errorClickedBtn != null) {
            errorClickedBtn
                    .setBackgroundColor(Color.parseColor("#4775ba"));
        }
        num1left = random.nextInt(30 + myScore * 5);
        if (CheckForNotLargeNumbers(num1left)){
            num1left = random.nextInt(30 + myScore * 5);
        }

        num2left = random.nextInt(30 + myScore * 5);
        if (CheckForNotLargeNumbers(num2left)){
            num2left = random.nextInt(30 + myScore * 5);
        }
        mark = random.nextInt(2);
        if (mark == 0) {
            sum_left = num1left + num2left;
            mark_left.setText("+");
        } else {
            sum_left = num1left - num2left;
            mark_left.setText("-");
        }
        example_left.setText(num1left + "\n" + num2left);

        //Створення рандомного числа від якого буде залежати коли буде приклад з відповідюю "="
        int randomNumber = random.nextInt(4);
        //Якщо рандомне число буде дорівнювати 1, то правий приклад буде створюватись так щоб його сума/різниця дорівнювали сумі/різниці лівого прикладу
        if (randomNumber == 1 ){
            int num1Right = random.nextInt(30 + myScore * 5);
            int num2Right;
            if (num1Right > sum_left){
                num2Right = num1Right - sum_left;
                sum_right = num1Right - num2Right;
                mark_right.setText("-");
            }else {
                num2Right = sum_left - num1Right;
                sum_right = num1Right + num2Right;
                mark_right.setText("+");
            }
            example_right.setText(num1Right + "\n" + num2Right);

        }else {
            int num1Right = random.nextInt(30 + myScore * 5);
            int num2Right = random.nextInt(30 + myScore * 5);
            mark = random.nextInt(2);
            example_right.setText(num1Right + "\n" + num2Right);
            if (mark == 0) {
                sum_right = num1Right + num2Right;
                mark_right.setText("+");
            } else {
                sum_right = num1Right - num2Right;
                mark_right.setText("-");
            }
        }
        headerFragment.startTimer(VariablesInGame.ARRAY_TIMER_IN_GAME[2], new HeaderFragment.TimerListener() {
            @Override
            public void onTimerFinish() {
                showDialog();
            }
        });

    }

    void showDialog() {
        headerFragment.stopTimer();
        replayDialog.show(getFragmentManager(), myScore,3,myScore/2, new ReplayDialog.ReplayListener() {
            @Override
            public void onReplayClick() {
                myScore = 0;
                headerFragment.setScore(myScore);
                buildGame();
                replayDialog.dismiss();
            }

            @Override
            public void onBackClick() {
                finish();
            }
        });
    }

    public void game3Click(View view) {
        switch (((Button) view).getText().toString()) {
            case "<":
                if (sum_left < sum_right) {
                    myScore++;
                    buildGame();
                } else {
                    showReplay(view);
                }
                break;
            case "=":
                if (sum_left == sum_right) {
                    buildGame();
                    myScore++;
                } else {
                    showReplay(view);
                }
                break;
            case ">":
                if (sum_left > sum_right) {
                    buildGame();
                    myScore++;
                } else {
                    showReplay(view);
                }
                break;
        }
        if (myScore > bestScore) {
            bestScore = myScore;
            PreferenceHelper.writeBestScoreGame(3, bestScore);
            headerFragment.setBestScore(bestScore);
        }
        headerFragment.setScore(myScore);
    }

    private void showReplay(final View view) {
        errorClickedBtn = ((SquareButton) view);
        errorClickedBtn.setBackgroundColor(Color.RED);
        showDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        headerFragment.stopTimer();
    }

    public static boolean CheckForNotLargeNumbers(int number){
        boolean bool = false;
        for (int i = -1; i<5; i++){
            if (number == i){
                bool = true;
            }else {
                bool = false;
            }
        }

        return bool;
    }
}
