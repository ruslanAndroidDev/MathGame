package fast.kopach.math.games;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import fast.kopach.math.R;

/**
 * Created by Руслан on 16.09.2017.
 */

public class ReplayFragment extends DialogFragment implements View.OnClickListener {
    ReplayListener listener;
    ImageView replay;
    ImageView back;
    ImageView setting;

    public ReplayFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.replay_dialog, null);
        builder.setView(v);
        replay = (ImageView) v.findViewById(R.id.replay_replay);
        replay.setOnClickListener(this);
        back = (ImageView) v.findViewById(R.id.replay_back);
        back.setOnClickListener(this);
        setting = (ImageView) v.findViewById(R.id.replay_setting);
        setting.setOnClickListener(this);
        return builder.create();
    }

    public void show(FragmentManager manager, String tag, ReplayListener listener) {
        super.show(manager, tag);
        this.listener = listener;
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
