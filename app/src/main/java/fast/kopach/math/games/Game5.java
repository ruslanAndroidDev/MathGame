package fast.kopach.math.games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.customView.PuzzleGameView;
import fast.kopach.math.dialogs.ReplayDialog;

public class Game5 extends AppCompatActivity {

    private HeaderFragment headerFragment;
    PuzzleGameView puzzleGameView;
    private ReplayDialog replayDialog;
    private int myScore = 0;
    private HeaderFragment.TimerListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);
        replayDialog = new ReplayDialog(this);

        puzzleGameView = (PuzzleGameView) findViewById(R.id.puzzleView);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
        listener = new HeaderFragment.TimerListener() {
            @Override
            public void onTimerFinish() {
                showErrorDialog();
            }
        };
//        headerFragment.startTimer(20, listener);
        headerFragment.setBestScore(PreferenceHelper.getBestScoreGame(5, this));
    }

    public void onBtnClick(View view) {
        if (puzzleGameView.checkAnswer() == 4) {
            myScore++;
            puzzleGameView.buildGame(myScore);
//            headerFragment.startTimer(20, listener);
            headerFragment.setScore(myScore);
            if (myScore > headerFragment.bestScore) {
                PreferenceHelper.writeBestScoreGame(5, myScore);
                headerFragment.setBestScore(myScore);
            }
        } else {
            showErrorDialog();
        }
    }

    private void showErrorDialog() {
        replayDialog.show(getFragmentManager(), myScore,5,myScore*2, new ReplayDialog.ReplayListener() {
            @Override
            public void onReplayClick() {
                myScore = 0;
                puzzleGameView.buildGame(myScore);
                headerFragment.setScore(myScore);
                replayDialog.dismiss();
            }

            @Override
            public void onBackClick() {
                finish();
            }
        });
    }
}
