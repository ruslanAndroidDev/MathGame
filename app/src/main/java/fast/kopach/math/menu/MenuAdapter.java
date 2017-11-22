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
                return new MenuItemFragment(R.drawable.texture1s, "I'm a calculator",1,false);
            case 1:
                return new MenuItemFragment(R.drawable.texture2, "Something is missing",2,false);
            case 2:
                return new MenuItemFragment(R.drawable.texture3s, "More or less",3,false);
            case 3:
                return new MenuItemFragment(R.drawable.texture4, "In order",4,false);
            case 4:
                return new MenuItemFragment(R.drawable.locked_padlock, "Puzzle",5,true);
            case 5:
                return new MenuItemFragment(R.drawable.locked_padlock, "Detective",6,true);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
