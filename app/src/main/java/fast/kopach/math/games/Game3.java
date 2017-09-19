package fast.kopach.math.games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

import fast.kopach.math.PreferenceHelper;
import fast.kopach.math.R;

public class Game3 extends AppCompatActivity {

    private HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        PreferenceHelper.setLaunchedGame(3);

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
    }
}
