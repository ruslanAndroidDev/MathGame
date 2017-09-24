package fast.kopach.math.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.Utill;
import fast.kopach.math.games.Game1;
import fast.kopach.math.games.Game2;
import fast.kopach.math.games.Game3;
import fast.kopach.math.games.Game4;
import fast.kopach.math.games.Game5;
import fast.kopach.math.games.Game6;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuItemFragment extends Fragment implements View.OnClickListener {
    int icon;
    String title;
    ImageView imageView;
    TextView textView;
    int position;
    Intent intent;

    public MenuItemFragment(int drawable, String title, int position) {
        this.icon = drawable;
        this.title = title;
        this.position = position;
        intent = new Intent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu, container, false);
        imageView = v.findViewById(R.id.menu_iv);
        textView = v.findViewById(R.id.menu_tv);
        textView.setText(title);
        if (position != 3 & position != 1& position != 2) {
            Picasso.with(getContext()).load(icon).fit().into(imageView);
        } else {
            imageView.setImageResource(icon);
        }
        v.setOnClickListener(this);
        v.setSoundEffectsEnabled(false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (position) {
            case 1:
                intent.setClass(getContext(), Game1.class);
                PreferenceHelper.launchedGame = "game 1";
                break;
            case 2:
                intent.setClass(getContext(), Game2.class);
                PreferenceHelper.launchedGame = "game 2";
                break;
            case 3:
                intent.setClass(getContext(), Game3.class);
                PreferenceHelper.launchedGame = "game 3";
                break;
            case 4:
                intent.setClass(getContext(), Game4.class);
                PreferenceHelper.launchedGame = "game 4";
                break;
            case 5:
                intent.setClass(getContext(), Game5.class);
                PreferenceHelper.launchedGame = "game 5";
                break;
            case 6:
                intent.setClass(getContext(), Game6.class);
                PreferenceHelper.launchedGame = "game 6";
                break;
        }
        startActivity(intent);
        Utill.playSound(getContext());
    }

}

