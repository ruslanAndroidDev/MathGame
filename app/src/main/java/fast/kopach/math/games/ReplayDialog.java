package fast.kopach.math.games;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.Random;

import fast.kopach.math.Calculation;
import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.Utill;

/**
 * Created by Руслан on 16.09.2017.
 */

public class ReplayDialog extends DialogFragment implements View.OnClickListener {
    ReplayListener listener;
    ImageView replay;
    ImageView back;
    ImageView setting;
    private int score;
    private int bestScore;
    TextView scoreTv, textScoreTV, textToProgressBarTV;
    Random random;
    RoundCornerProgressBar progress1;

    boolean isLoadInterstialAd = false;

    private InterstitialAd mInterstitialAd;
    Context context;

    public ReplayDialog(final Context context) {
        this.context = context;
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-8320045635693885/7405754217");
        random = new Random();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        bestScore = PreferenceHelper.getBestScoreGame(PreferenceHelper.launchedGame, getActivity().getApplicationContext());

        MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-8320045635693885~7488509104");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.replay_dialog, null);
        builder.setView(v);
        final View parentLayout = v.findViewById(R.id.parentLayout);

        progress1 = (RoundCornerProgressBar) v.findViewById(R.id.roundCornerProgressBar);
        progress1.setProgressColor(Color.parseColor("#ed3b27"));
        progress1.setProgressBackgroundColor(Color.parseColor("#808080"));
        progress1.setMax(Calculation.getScoreBoundaryPoint(bestScore));
        progress1.setProgress(bestScore);

        final NativeExpressAdView adView = new NativeExpressAdView(getActivity());
        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //дізнаємося ширину dialog і показуємо рекламу
                int width = (int) Utill.convertPixelsToDp(parentLayout.getWidth(), getActivity());
                parentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (width > 280) {
                    Log.d("tag", "width>280");
                    adView.setAdSize(new AdSize(width, 80));
                    adView.setAdUnitId(getString(R.string.nativeAdView1));
                    AdRequest request = new AdRequest.Builder().build();
                    adView.loadAd(request);
                    adView.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            ((LinearLayout) parentLayout).addView(adView);
                        }
                    });
                } else {
                    isLoadInterstialAd = true;
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                }
            }
        });

        replay = (ImageView) v.findViewById(R.id.replay_replay);
        replay.setOnClickListener(this);
        back = (ImageView) v.findViewById(R.id.replay_back);
        back.setOnClickListener(this);
        setting = (ImageView) v.findViewById(R.id.replay_setting);
        setting.setOnClickListener(this);

        scoreTv = (TextView) v.findViewById(R.id.replay_score_tv);
        // textScoreTV = (TextView) v.findViewById(R.id.replay_text_score);
        textToProgressBarTV = (TextView) v.findViewById(R.id.replay_text_progress_bar);
        scoreTv.setText("" + score);
        textToProgressBarTV.setText(bestScore + "/" + Calculation.getScoreBoundaryPoint(bestScore));
        builder.setCancelable(false);
        setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager manager, int score, ReplayListener listener) {
        super.show(manager, "");
        this.listener = listener;
        this.score = score;
        // this.bestScore = bestScore;

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.replay_back:
                listener.onBackClick();
                break;
            case R.id.replay_replay:
                if (isLoadInterstialAd & random.nextInt(4) == 0) {
                    Log.d("tag", "random=4");
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
                listener.onReplayClick();
                break;
            case R.id.replay_setting:
                break;
        }
    }

    interface ReplayListener {
        abstract void onReplayClick();

        abstract void onBackClick();
    }

    public void interstitialAdShow() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}