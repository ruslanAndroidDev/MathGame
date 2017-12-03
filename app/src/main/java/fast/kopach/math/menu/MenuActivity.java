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
import fast.kopach.math.Broadcast;
import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.games.VariablesInGame;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuActivity extends AppCompatActivity {
    ViewPager viewPager;
    MenuAdapter adapter;
    TextView tv_coin;

    Broadcast broadcast;
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

        broadcast = new Broadcast();
        startRepeatingTimer(); //Початок відліку часу, після чого з'явиться notification і скаже що користувач отримав в подарунок монети

        // Перше створення SharedPreff, не додумався куди вписати, тому написав тут
        PreferenceHelper.firstCreateSharedPref(this);
        PreferenceHelper.launchGame(this);

        viewPager = (ViewPager) findViewById(R.id.menuViewPager);
        adapter = new MenuAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tv_coin = (TextView) findViewById(R.id.tv_coin);
    }

    @Override
    public void onResume() {
      //  rewardedVideoAd.resume(this);
        tv_coin.setText(PreferenceHelper.getCoin() + "");

        if (PreferenceHelper.getCountReplayShow() > PreferenceHelper.getBoundaryForShowRate()){
            if (PreferenceHelper.getShowRatedGame()){
                showRateDialog();
            }
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        // rewardedVideoAd.pause(this);
        //  showRewardedVideoAd();
        super.onPause();
    }

    @Override
    public void onDestroy() {
      //  rewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    public void onMenuButtonClick(View view) {
        if (view.getId() == R.id.btn_exit) {
            finish();
        } else if (view.getId() == R.id.btn_setting) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void showRateDialog() {
        AppEvaluationDialog dialog = new AppEvaluationDialog();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void startRepeatingTimer(){
      //  Context context= this.getApplicationContext();
        if(broadcast!=null){
            broadcast.SetAlarm(this);
        }else{
           // Toast.makeText(this,"Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }
}
