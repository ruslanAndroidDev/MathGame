package fast.kopach.math.games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

import fast.kopach.math.R;

public class Game3 extends AppCompatActivity {

    private HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
    }
}
