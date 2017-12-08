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
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.Utill;
import fast.kopach.math.games.VariablesInGame;

/**
 * Created by Руслан on 16.09.2017.
 */

public class InfoDialog extends DialogFragment {
    TextView tvHowPlay;
    Context context;
    int drawableForInfo = 0;
    String textHowPlay;

    public InfoDialog(Context context) {
         this.context = context;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_info_about_coin, null);
        builder.setView(v);

        tvHowPlay = (TextView) v.findViewById(R.id.tvHowPlay);
       // ImageView iView = (ImageView) v.findViewById(R.id.iv_game);

        //Налаштування виводу меж в рівнях в залежності від гри
        switch (PreferenceHelper.launchedGame) {
            case 1:
               drawableForInfo = R.drawable.game_1;
               textHowPlay = "Input the answer to the example";
                break;
            case 2:
                drawableForInfo = R.drawable.game_2;
                textHowPlay = "Click on the missing number";
                break;
            case 3:
                drawableForInfo = R.drawable.game_3;
                textHowPlay = "Select the missing sign";
                break;
            case 4:
                drawableForInfo = R.drawable.game_4;
                textHowPlay = "Select numbers in ascending order";
                break;
            case 5:
                drawableForInfo = R.drawable.game_1;
                textHowPlay = "Input the answer to the example";
                break;
            case 6:
                drawableForInfo = R.drawable.game_6;
                textHowPlay = "Find the wrong example";
                break;
        }

        tvHowPlay.setText(textHowPlay);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_game);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(drawableForInfo).into(imageViewTarget);

       // Glide.with(context).
            //    load(R.drawable.image).into(imageView);

        builder.setCancelable(false);
        return builder.create();
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, "");
    }
}