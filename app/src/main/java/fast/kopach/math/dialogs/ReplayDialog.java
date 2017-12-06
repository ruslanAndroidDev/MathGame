package fast.kopach.math.dialogs;

import android.app.Activity;
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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.Random;

import fast.kopach.math.Advertising;
import fast.kopach.math.AppEvaluationDialog;
import fast.kopach.math.Calculation;
import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.Utill;
import fast.kopach.math.games.HeaderFragment;
import fast.kopach.math.games.VariablesInGame;

/**
 * Created by Руслан on 16.09.2017.
 */

public class ReplayDialog extends DialogFragment implements View.OnClickListener {
    Advertising advertising;
    ReplayListener listener;
    ImageView replay,setting,back;
    private int score;
    TextView scoreTv, tvGameName,coinTv,bestScoreTv;
    Random random;
    InfoDialog infoDialog;

    Context context;
    private int coin;
    String name;
    private int game;

    public ReplayDialog(final Context context) {
        this.context = context;
        random = new Random();
        advertising = new Advertising(context);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-8320045635693885~7488509104");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.replay_dialog, null);
        builder.setView(v);
        final View parentLayout = v.findViewById(R.id.parentLayout);

        infoDialog = new InfoDialog(context);

        //Алгоритм що вирішує коли буде нативна а коли міжсторінкова реклама
        VariablesInGame.isShowInterstitialAd = false;
        for (int i = 0; i < VariablesInGame.boundary_point_show_replay_for_ad.length; i++){
            if (VariablesInGame.SHOW_REPLAY_COUNT == VariablesInGame.boundary_point_show_replay_for_ad[i]){
                advertising.loadInterstitialAd();
            }else if (VariablesInGame.SHOW_REPLAY_COUNT - 1 == VariablesInGame.boundary_point_show_replay_for_ad[i]){
                VariablesInGame.isShowInterstitialAd = true;
                advertising.showInterstitialAd();
            }
        }

        if (!VariablesInGame.isShowInterstitialAd){
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

                    }
                }
            });
        }

        bestScoreTv = v.findViewById(R.id.replay_best_score_tv);
        bestScoreTv.setText(PreferenceHelper.getBestScoreGame(game,getActivity())+"");
        coinTv = v.findViewById(R.id.coinTv);
        replay = v.findViewById(R.id.replay_replay);
        replay.setOnClickListener(this);
        back = v.findViewById(R.id.replay_back);
        back.setOnClickListener(this);
        setting = v.findViewById(R.id.replay_setting);
        setting.setOnClickListener(this);

        scoreTv = v.findViewById(R.id.replay_score_tv);
        tvGameName = v.findViewById(R.id.tv_game_name);
        scoreTv.setText("" + score);
        tvGameName.setText(name);
        coinTv.setText(coin+"");

        setColorTextScore(score, PreferenceHelper.getBestScoreGame(game,getActivity()));


      /*  LinearLayout linear = (LinearLayout) v.findViewById(R.id.parentLayout);
        AdView ad = new AdView(getActivity());
        ad.setAdSize(AdSize.BANNER);
        ad.setAdUnitId("ca-app-pub-8320045635693885/6995257268");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("*************")
                .build();


        linear.addView(ad);
        ad.loadAd(adRequest);  */

        builder.setCancelable(false);
        setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager manager, int score,int game,int coin, ReplayListener listener) {
        super.show(manager, "");
        this.listener = listener;
        this.score = score;
        this.game = game;
        this.name = Utill.getGameName(game);
        this.coin = coin;

        VariablesInGame.SHOW_REPLAY_COUNT++;
        PreferenceHelper.setCountReplayShow(1);
        PreferenceHelper.addCoin(coin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.replay_back:
                listener.onBackClick();
                break;
            case R.id.replay_replay:
                listener.onReplayClick();
                break;
            case R.id.replay_setting:
                infoDialog.show(getFragmentManager());
                break;
        }
    }

    public interface ReplayListener {
        abstract void onReplayClick();

        abstract void onBackClick();
    }

    public void setColorTextScore(int score, int bestScore){
        if (score == bestScore){
            scoreTv.setTextColor(Color.parseColor("#388e3c"));
        }else {
            scoreTv.setTextColor(Color.parseColor("#4775ba"));
        }
    }
}