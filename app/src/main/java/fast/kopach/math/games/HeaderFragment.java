package fast.kopach.math.games;

import android.os.Bundle;
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
    int bestScore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.score_element, container, false);
        best_score_tv = (TextView) v.findViewById(R.id.tv_bs);
        score_tv = (TextView) v.findViewById(R.id.tv_score);
        setScore(0);
        setBestScore(1);
        return v;
    }

    public void setScore(int score) {
        String text = "<font color=#ff000000>Score: </font> <font color=#0404B4>" + score + "</font>";
        score_tv.setText(Html.fromHtml(text));
        if (score > bestScore) {
            setBestScore(bestScore);
        }

    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
        String text = "<font color=#ff000000>BS: </font> <font color=#0404B4>" + bestScore + "</font>";
        best_score_tv.setText(Html.fromHtml(text));
    }
}
