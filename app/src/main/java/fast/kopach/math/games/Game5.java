package fast.kopach.math.games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fast.kopach.math.R;

public class Game5 extends AppCompatActivity {

    private HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game5);

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);
    }

}
