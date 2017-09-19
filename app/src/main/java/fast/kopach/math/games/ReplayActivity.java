package fast.kopach.math.games;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import fast.kopach.math.R;

/**
 * Created by Руслан on 16.09.2017.
 */

public class ReplayActivity extends AppCompatActivity implements View.OnClickListener {
  //  ReplayListener listener;
    ImageView replay;
    ImageView back;
    ImageView setting;

    private AdView mAdViewBanner1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replay_activity);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        // public void onCreate(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
      //  AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
      //  LayoutInflater inflater = getActivity().getLayoutInflater();
      //  View v = inflater.inflate(R.layout.replay_activity, null);
      //  builder.setView(v);
        replay = (ImageView) findViewById(R.id.replay_replay);
        replay.setOnClickListener(this);
        back = (ImageView) findViewById(R.id.replay_back);
        back.setOnClickListener(this);
        setting = (ImageView) findViewById(R.id.replay_setting);
        setting.setOnClickListener(this);

/////////////////////////////////////////////////////////////////////////
        mAdViewBanner1 = (AdView) findViewById(R.id.adViewBanner1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewBanner1.loadAd(adRequest);
        //////////////////////////////////////////////////

      //  return builder.create();
    }

  /*  public void show(FragmentManager manager, String tag, ReplayListener listener) {
        super.show(manager, tag);
        this.listener = listener;
    }  */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.replay_back:
               // listener.onBackClick();
                onBackClick();
                break;
            case R.id.replay_replay:
               // listener.onReplayClick();
                onReplayClick();
                break;
            case R.id.replay_setting:
               // listener.onSettingClick();
                onSettingClick();
                break;
        }
    }

    private void onSettingClick() {
    }

    private void onReplayClick() {
    }

    private void onBackClick() {
    }




   /* static abstract class ReplayListener {
        abstract void onReplayClick();

        abstract void onBackClick();

        abstract void onSettingClick();
    }  */
}
