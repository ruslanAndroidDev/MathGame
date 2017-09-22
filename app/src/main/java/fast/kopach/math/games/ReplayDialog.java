package fast.kopach.math.games;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
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
import com.google.android.gms.ads.NativeExpressAdView;

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
    TextView scoreTv;

    public ReplayDialog() {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.replay_dialog, null);
        builder.setView(v);
        final View parentLayout = v.findViewById(R.id.parentLayout);

        final NativeExpressAdView adView = new NativeExpressAdView(getActivity());
        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //дізнаємося ширину dialog і показуємо рекламу
                adView.setAdSize(new AdSize((int) convertPixelsToDp(parentLayout.getWidth(), getActivity()), 80));
                adView.setAdUnitId(getString(R.string.nativeAdView1));
                AdRequest request = new AdRequest.Builder().build();
                adView.loadAd(request);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.d("tag", "AdLoaded");
                        ((LinearLayout) parentLayout).addView(adView);
                    }
                });
                parentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        replay = (ImageView) v.findViewById(R.id.replay_replay);
        replay.setOnClickListener(this);
        back = (ImageView) v.findViewById(R.id.replay_back);
        back.setOnClickListener(this);
        setting = (ImageView) v.findViewById(R.id.replay_setting);
        setting.setOnClickListener(this);

        scoreTv = (TextView) v.findViewById(R.id.replay_score_tv);
        scoreTv.setText("" + score);
        builder.setCancelable(false);
        setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager manager, int score, String tag, ReplayListener listener) {
        super.show(manager, tag);
        this.listener = listener;
        this.score = score;
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
                listener.onSettingClick();
                break;
        }
    }

    static abstract class ReplayListener {
        abstract void onReplayClick();

        abstract void onBackClick();

        abstract void onSettingClick();
    }
}