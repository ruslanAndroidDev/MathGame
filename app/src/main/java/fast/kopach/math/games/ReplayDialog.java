package fast.kopach.math.games;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
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

    private InterstitialAd mInterstitialAd;
    Context context;
    int game;
    private int bestScore;

    public ReplayDialog(final Context context, int game) {
        this.context = context;
        this.game = game;
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-8320045635693885/7405754217");
        random = new Random();
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("tag", "onCreate");
        // Use the Builder class for convenient dialog construction

        MobileAds.initialize(getActivity().getApplicationContext(),"ca-app-pub-8320045635693885~7488509104");
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-8320045635693885/7405754217");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        timer = new CountDownTimer(6000, 1000) {
            public void onTick(long otschetdofinisha) {}
            public void onFinish() {
                mInterstitialAdShow();
            }
        }.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.replay_dialog, null);
        builder.setView(v);
        final View parentLayout = v.findViewById(R.id.parentLayout);

        final NativeExpressAdView adView = new NativeExpressAdView(getActivity());

        progress1 = (RoundCornerProgressBar) v.findViewById(R.id.roundCornerProgressBar);
        progress1.setProgressColor(Color.parseColor("#ed3b27"));
        progress1.setProgressBackgroundColor(Color.parseColor("#808080"));
        progress1.setMax(1);
        progress1.setProgress(0);

       // int progressColor = progress1.getProgressColor();
       // int backgroundColor = progress1.getProgressBackgroundColor();
       // int max = (int) progress1.getMax();
       // int progress = (int) progress1.getProgress();

        final NativeExpressAdView adView = new NativeExpressAdView(getActivity());
        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //дізнаємося ширину dialog і показуємо рекламу
                int width = (int) convertPixelsToDp(parentLayout.getWidth(), getActivity());
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
        textToProgressBarTV.setText(bestScore+"/"+Calculation.getScoreBoundaryPoint(bestScore));
        builder.setCancelable(false);
        setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager manager, int bestScore, int score, ReplayListener listener) {
        super.show(manager, "");
        this.listener = listener;
        this.score = score;
        progress1.setMax(Calculation.getScoreBoundaryPoint(bestScore));
        progress1.setProgress(bestScore);
        this.bestScore = bestScore;
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
                if (random.nextInt(4) == 0) {
                    Log.d("tag", "random=4");
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
                listener.onReplayClick();
                break;
            case R.id.replay_setting:
                break;
        }
    }

    static abstract class ReplayListener {
        abstract void onReplayClick();

        abstract void onBackClick();
    }

    public void interstitialAdShow(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
          //  timer.start();
        }
    }
}