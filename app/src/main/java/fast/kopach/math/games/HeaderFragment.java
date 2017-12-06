package fast.kopach.math.games;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fast.kopach.math.R;

/**
 * Created by Руслан on 15.09.2017.
 */

public class HeaderFragment extends Fragment {
    TextView score_tv;
    TextView best_score_tv;
    TextView timerTv;
    int bestScore;
    int seconds;
    CountDownTimer timer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.score_element, container, false);
        best_score_tv = (TextView) v.findViewById(R.id.tv_bs);
        score_tv = (TextView) v.findViewById(R.id.tv_score);
        timerTv = (TextView) v.findViewById(R.id.timerTv);
        setScore(0);
        return v;
    }

    public void setScore(int score) {
        String text = "<font color=#ff000000>Score: </font> <font color=#4775ba>" + score + "</font>";
        score_tv.setText(Html.fromHtml(text));

    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
        String text = "<font color=#ff000000>BS: </font> <font color=#388e3c>" + bestScore + "</font>";
        best_score_tv.setText(Html.fromHtml(text));
    }

    public void stopTimer() {
        timer.cancel();
    }

    public void startTimer(final int sec, final TimerListener listener) {
        seconds = sec;
        if (timer == null) {
            timer = new CountDownTimer(seconds * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    seconds -= 1;
                    timerTv.setText(String.valueOf(seconds));
                }

                @Override
                public void onFinish() {
                    listener.onTimerFinish();
                }
            }.start();
        } else {
            timer.cancel();
            timer.start();
        }
    }

    interface TimerListener {
        void onTimerFinish();
    }
}
