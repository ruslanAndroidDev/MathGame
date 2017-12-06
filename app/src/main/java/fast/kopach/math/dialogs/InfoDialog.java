package fast.kopach.math.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.Utill;
import fast.kopach.math.games.VariablesInGame;

/**
 * Created by Руслан on 16.09.2017.
 */

public class InfoDialog extends DialogFragment {
    TextView tvClose;
    Context context;
    int drawableForInfo = 0;

    public InfoDialog(Context context) {
         this.context = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_info_about_coin, null);
        builder.setView(v);

        tvClose = (TextView) v.findViewById(R.id.info_tv_close);
        ImageView iView = (ImageView) v.findViewById(R.id.iv_game);

//        tvGameName.setText(Utill.getGameName(PreferenceHelper.launchedGame));

        //Налаштування виводу меж в рівнях в залежності від гри
        switch (PreferenceHelper.launchedGame) {
            case 1:
               drawableForInfo = R.drawable.game_1;
                break;
            case 2:
                drawableForInfo = R.drawable.game_1;
                break;
            case 3:
                drawableForInfo = R.drawable.game_1;
                break;
            case 4:
                drawableForInfo = R.drawable.game_1;
                break;
            case 5:
                drawableForInfo = R.drawable.game_1;
                break;
            case 6:
                drawableForInfo = R.drawable.game_1;
                break;
        }

        Glide.with(context).
                load(drawableForInfo).into(iView);

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        builder.setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, "");
    }
}