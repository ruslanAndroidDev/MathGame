package fast.kopach.math.menu;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fast.kopach.math.R;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuAdapter extends FragmentStatePagerAdapter {
    public MenuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MenuItemFragment(R.drawable.texture1s, "Game1",1);
            case 1:
                return new MenuItemFragment(R.drawable.texture2, "Game2",2);
            case 2:
                return new MenuItemFragment(R.drawable.texture3s, "Game3",3);
            case 3:
                return new MenuItemFragment(R.drawable.texture4, "Game4",4);
            case 4:
                return new MenuItemFragment(R.drawable.texture5, "Game5",5);
            case 5:
                return new MenuItemFragment(R.drawable.texture6, "Game6",6);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
