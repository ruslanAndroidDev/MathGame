package fast.kopach.math.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fast.kopach.math.PreferenceHelper;
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
                return new MenuItemFragment(R.drawable.texture1s, "I'm a calculator", 1, true);
            case 1:
                return new MenuItemFragment(R.drawable.texture2, "Something is missing", 2, true);
            case 2:
                return new MenuItemFragment(R.drawable.texture3s, "More or less", 3, true);
            case 3:
                return new MenuItemFragment(R.drawable.texture4, "In order", 4, PreferenceHelper.isOpenGame(4));
            case 4:
                return new MenuItemFragment(R.drawable.texture5, "Detective", 5, PreferenceHelper.isOpenGame(5));
           // case 5:
               // return new MenuItemFragment(R.drawable.texture6, "Puzzle", 6, PreferenceHelper.isOpenGame(6));

        }
        return null;
    }


    @Override
    public int getCount() {
        return 5;
    }
}
