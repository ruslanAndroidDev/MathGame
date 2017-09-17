package fast.kopach.math.games;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fast.kopach.math.R;

public class Game3 extends AppCompatActivity {

    private HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
    }
}
