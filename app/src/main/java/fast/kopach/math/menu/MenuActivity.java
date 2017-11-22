package fast.kopach.math.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import fast.kopach.math.AppEvaluationDialog;
import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuActivity extends AppCompatActivity implements RewardedVideoAdListener {
    ViewPager viewPager;
    MenuAdapter adapter;
    TextView tv_coin;

    RewardedVideoAd rewardedVideoAd;

    private AdView mAdView;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);

// init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

// change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


        // Перше створення SharedPreff, не додумався куди вписати, тому написав тут
        PreferenceHelper.firstCreateSharedPref(this);

        showDialog();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8320045635693885~7488509104");
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);

        viewPager = (ViewPager) findViewById(R.id.menuViewPager);
        adapter = new MenuAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tv_coin = (TextView) findViewById(R.id.tv_coin);
        //  loadRewardedVideoAd();
    }

    private void loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-8320045635693885/3778039399", new AdRequest.Builder().build());
        Toast.makeText(this, "Завантаження реклами розпочалось", Toast.LENGTH_LONG).show();
    }

    public void showRewardedVideoAd() {
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
            Toast.makeText(this, "Відео з рекламою завантажено", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Відео з рекламою не завантажено", Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, "Ви отримали " + rewardItem.getAmount() + " " + rewardItem.getType(), Toast.LENGTH_LONG).show();
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
//        tv_coin.setText("" + PreferenceHelper.getCoin());
        super.onResume();
    }

    @Override
    public void onPause() {
        rewardedVideoAd.pause(this);
        //  showRewardedVideoAd();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    public void onMenuButtonClick(View view) {
        if (view.getId() == R.id.btn_exit) {
            finish();
        }else if(view.getId()==R.id.btn_setting){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void showDialog() {
        AppEvaluationDialog dialog = new AppEvaluationDialog();
        dialog.show(getSupportFragmentManager(), "custom");
    }
}
