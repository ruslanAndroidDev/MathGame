package fast.kopach.math.menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;
import fast.kopach.math.Utill;
import fast.kopach.math.games.Game1;
import fast.kopach.math.games.Game2;
import fast.kopach.math.games.Game3;
import fast.kopach.math.games.Game4;
import fast.kopach.math.games.Game6;
import fast.kopach.math.games.Game5;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuItemFragment extends Fragment implements View.OnClickListener {
    int icon;
    String title;
    ImageView imageView;
    TextView textView;
    TextView unlockTv;
    CardView cardView;
    int position;
    Intent intent;
    boolean isOpen;

    public MenuItemFragment(int drawable, String title, int position, boolean isOpen) {
        this.icon = drawable;
        this.title = title;
        this.position = position;
        this.isOpen = isOpen;
        intent = new Intent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu, container, false);
        imageView = v.findViewById(R.id.menu_iv);
        textView = v.findViewById(R.id.menu_tv);
        unlockTv = v.findViewById(R.id.unlockTv);
        cardView = v.findViewById(R.id.menuCardView);
        if (!isOpen) {
            cardView.setCardBackgroundColor(Color.parseColor("#FF454444"));
            textView.setTextColor(Color.WHITE);
            imageView.setImageResource(R.drawable.locked_padlock);
            unlockTv.setVisibility(View.VISIBLE);
            unlockTv.setText("Unlock for " + PreferenceHelper.getPrice(position) + " ©");
        } else {
            if (position != 3 & position != 1 & position != 2) {
                Picasso.with(getContext()).load(icon).fit().into(imageView);
            } else {
                imageView.setImageResource(icon);
            }
        }
        textView.setText(title);
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
        if (PreferenceHelper.isOpenGame(position)) {
            switch (position) {
                case 1:
                    intent.setClass(getContext(), Game1.class);
                    break;
                case 2:
                    intent.setClass(getContext(), Game2.class);
                    break;
                case 3:
                    intent.setClass(getContext(), Game3.class);
                    break;
                case 4:
                    intent.setClass(getContext(), Game4.class);
                    break;
                case 5:
                    intent.setClass(getContext(), Game5.class);
                    break;
                case 6:
                    intent.setClass(getContext(), Game6.class);
                    break;
            }
            Utill.playSound(getContext());
            startActivity(intent);
        } else {
            if (PreferenceHelper.getCoin() >= PreferenceHelper.getPrice(position)) {
                unlockGame();
            } else {
                Toast.makeText(getContext(), "You dont have enought maney", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void unlockGame() {
        PreferenceHelper.setCoin(PreferenceHelper.getCoin() - PreferenceHelper.getPrice(position));
        PreferenceHelper.openGame(position);
        unlockTv.setVisibility(View.GONE);
        cardView.setCardBackgroundColor(Color.WHITE);
        imageView.setImageResource(icon);
    }
}

