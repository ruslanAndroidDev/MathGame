package fast.kopach.math.games;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class InfoDialog extends DialogFragment implements View.OnClickListener {

    TextView tvScore1, tvScore2, tvScore3, tvScore4, tvScore5, tvCoin1, tvCoin2, tvCoin3, tvCoin4, tvCoin5, tvGameName;
    TextView tvClose;

   // Context context;

    public InfoDialog() {
       // this.context = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_info_about_coin, null);
        builder.setView(v);
        final View parentLayout = v.findViewById(R.id.parentLayout);

        tvScore1 = (TextView) v.findViewById(R.id.info_tv_score_1);
        tvScore2 = (TextView) v.findViewById(R.id.info_tv_score_2);
        tvScore3 = (TextView) v.findViewById(R.id.info_tv_score_3);
        tvScore4 = (TextView) v.findViewById(R.id.info_tv_score_4);
        tvScore5 = (TextView) v.findViewById(R.id.info_tv_score_5);
        tvCoin1 = (TextView) v.findViewById(R.id.info_tv_coin_1);
        tvCoin2 = (TextView) v.findViewById(R.id.info_tv_coin_2);
        tvCoin3 = (TextView) v.findViewById(R.id.info_tv_coin_3);
        tvCoin4 = (TextView) v.findViewById(R.id.info_tv_coin_4);
        tvCoin5 = (TextView) v.findViewById(R.id.info_tv_coin_5);
        tvGameName = (TextView) v.findViewById(R.id.info_tv_game_name);
        tvClose = (TextView) v.findViewById(R.id.info_tv_close);

        TextView tvScore[] = {tvScore1, tvScore2, tvScore3, tvScore4, tvScore5};
        TextView tvCoin[] = {tvCoin1, tvCoin2, tvCoin3, tvCoin4, tvCoin5};

        tvGameName.setText(Utill.getGameName(PreferenceHelper.launchedGame));

        //Налаштування виводу меж в рівнях в залежності від гри
        switch (PreferenceHelper.launchedGame){
            case 1:
                for (int i = 0; i <= 4; i++ ) {
                    tvScore[i].setText("" + VariablesInGame.ARRAY_GAME_1_SCORE_BOUNDARY_POINT[i]);
                }
                break;
            case 2:
                for (int i = 0; i <= 4; i++ ) {
                    tvScore[i].setText("" + VariablesInGame.ARRAY_GAME_2_SCORE_BOUNDARY_POINT[i]);
                }
                break;
            case 3:
                for (int i = 0; i <= 4; i++ ) {
                    tvScore[i].setText("" + VariablesInGame.ARRAY_GAME_3_SCORE_BOUNDARY_POINT[i]);
                }
                break;
            case 4:
                for (int i = 0; i <= 4; i++ ) {
                    tvScore[i].setText("" + VariablesInGame.ARRAY_GAME_4_SCORE_BOUNDARY_POINT[i]);
                }
                break;
            case 5:
                for (int i = 0; i <= 4; i++ ) {
                    tvScore[i].setText("" + VariablesInGame.ARRAY_GAME_5_SCORE_BOUNDARY_POINT[i]);
                }
                break;
            case 6:
                for (int i = 0; i <= 4; i++ ) {
                    tvScore[i].setText("" + VariablesInGame.ARRAY_GAME_6_SCORE_BOUNDARY_POINT[i]);
                }
                break;
        }

        //Налаштування виводу монет в рівнях в залежності від гри
        for (int i = 0; i <= 4; i++ ) {
            tvCoin[i].setText("" + VariablesInGame.ARRAY_GAME_ADD_COIN[i]);
        }

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        builder.setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager manager) {
        super.show(manager, "");
      //  this.listener = listener;
    }

    @Override
    public void onClick(View view) {

    }


}