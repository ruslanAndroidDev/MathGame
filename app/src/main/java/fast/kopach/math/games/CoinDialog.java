package fast.kopach.math.games;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import fast.kopach.math.R;

/**
 * Created by Руслан on 14.10.2017.
 */

public class CoinDialog extends ReplayDialog {
    public CoinDialog(Context context) {
        super(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.coins, null);
        builder.setView(v);
        return builder.create();
    }
}
