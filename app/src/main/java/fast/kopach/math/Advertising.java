package fast.kopach.math;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import fast.kopach.math.games.VariablesInGame;

/**
 * Created by Vova on 18.11.2017.
 */

public class Advertising implements RewardedVideoAdListener {

    InterstitialAd mInterstitialAd;
    RewardedVideoAd rewardedVideoAd;
    Context context;

    public Advertising(Context context) {
        this.context = context;

        MobileAds.initialize(context, "ca-app-pub-8320045635693885~7488509104");
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.setRewardedVideoAdListener(this);

        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-8320045635693885/7405754217");
    }

    public void loadInterstitialAd(){
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void showInterstitialAd(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void loadRewardedVideoAd(Context context) {
        rewardedVideoAd.loadAd("ca-app-pub-8320045635693885/3778039399", new AdRequest.Builder().build());
    }

    public void showRewardedVideoAd(Context context) {
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }


    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
