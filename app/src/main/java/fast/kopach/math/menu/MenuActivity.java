package fast.kopach.math.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fast.kopach.math.R;

/**
 * Created by Руслан on 11.09.2017.
 */

public class MenuActivity extends AppCompatActivity {
    ViewPager viewPager;
    MenuAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.menuViewPager);
        adapter = new MenuAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public void onMenuButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                finish();
                break;
            case R.id.btn_coin:
                break;
            case R.id.btn_setting:
                break;
        }
    }
}
