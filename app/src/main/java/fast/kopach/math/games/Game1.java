package fast.kopach.math.games;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;

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
    // PreferenceHelper preferenceHelper;

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
        replayDialog = new ReplayDialog();
        //  replayActivity = new ReplayActivity();
        //  preferenceHelper = new PreferenceHelper();

        PreferenceHelper.setLaunchedGame(1);

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
                    PreferenceHelper.writeBestScoreGame(1, bestScore, this);
                    headerFragment.setBestScore(bestScore);
                }
                headerFragment.setScore(score);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        buildGame();
                    }
                }, 500);
            } else {
                score = 0;
                headerFragment.setScore(score);
                replayDialog.show(getFragmentManager(), "", null);
                // replayActivity.dismiss();
                // buildGame();
               /* replayActivity.show(getFragmentManager(), "", new ReplayActivity.ReplayListener() {
                    @Override
                    void onReplayClick() {
                        Log.d("tag", "replayClick");
                        score = 0;
                        headerFragment.setScore(score);
                        replayActivity.dismiss();
                        buildGame();
                    }

                    @Override
                    void onBackClick() {

                    }

                    @Override
                    void onSettingClick() {

                    }
                }); */
            }
        } else {
            Toast.makeText(this, "Введіть відповідь!", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildGame() {
        int prykladrandom = (int) (Math.random() * 2);

        myAnswer = "";

        min_value = 50 + score * 15;
        max_value = 70 + score * 15;
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
    }

}
