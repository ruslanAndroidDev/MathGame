package fast.kopach.math.games;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.customView.SquareButton;

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
        bestScore = PreferenceHelper.getBestScoreGame(3);
        headerFragment.setBestScore(bestScore);
        replayDialog = new ReplayDialog(this);

        buildGame();
    }

    private void buildGame() {
        num1left = random.nextInt(30 + myScore * 5);
        num2left = random.nextInt(30 + myScore * 5);
        mark = random.nextInt(2);
        if (mark == 0) {
            sum_left = num1left + num2left;
            mark_left.setText("+");
        } else {
            sum_left = num1left - num2left;
            mark_left.setText("-");
        }
        Log.d("tag", "LEFT" + num1left + mark_left.getText() + num2left + "=" + sum_left);
        example_left.setText(num1left + "\n" + num2left);

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

    public void game3Click(View view) {
        Log.d("tag", "sumLeft" + sum_left + ",sumRight " + sum_right);
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
        ((SquareButton) view).setBackgroundColor(Color.RED);
        replayDialog.show(getFragmentManager(), myScore, new ReplayDialog.ReplayListener() {
            @Override
            void onReplayClick() {
                myScore = 0;
                headerFragment.setScore(myScore);
                buildGame();
                ((SquareButton) view).setBackgroundColor(Color.parseColor("#4775ba"));
                replayDialog.dismiss();
            }

            @Override
            void onBackClick() {
                finish();
            }
        });
    }
}
