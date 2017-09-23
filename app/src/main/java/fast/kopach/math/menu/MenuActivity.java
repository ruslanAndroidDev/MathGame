package fast.kopach.math.menu;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import fast.kopach.math.R;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuActivity extends AppCompatActivity implements RewardedVideoAdListener{
    ViewPager viewPager;
    MenuAdapter adapter;

    RewardedVideoAd rewardedVideoAd;

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "");

        viewPager = (ViewPager) findViewById(R.id.menuViewPager);
        adapter = new MenuAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8320045635693885~7488509104");

       // mAdView = (AdView) findViewById(R.id.bannerAdView1);
       // AdRequest adRequest = new AdRequest.Builder().build();
       // mAdView.loadAd(adRequest);


        //  loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-8320045635693885/3778039399", new AdRequest.Builder().build());
        Toast.makeText(this, "Завантаження реклами розпочалось", Toast.LENGTH_LONG).show();
    }

    public void showRewardedVideoAd(){
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
            Toast.makeText(this, "Відео з рекламою завантажено", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Відео з рекламою не завантажено", Toast.LENGTH_LONG).show();}
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

    @Override
    public void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        rewardedVideoAd.pause(this);
       // showRewardedVideoAd();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }
}
