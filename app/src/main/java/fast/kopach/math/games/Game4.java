package fast.kopach.math.games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import fast.kopach.math.R;

public class Game4 extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16,
            btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24;
    Button[] buttonsArray;
    private int bound = 24;
    ArrayList<Integer> values;
    private HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.header);

        btn1 = (Button) findViewById(R.id.game4_btn1);
        btn2 = (Button) findViewById(R.id.game4_btn2);
        btn3 = (Button) findViewById(R.id.game4_btn3);
        btn4 = (Button) findViewById(R.id.game4_btn4);
        btn5 = (Button) findViewById(R.id.game4_btn5);
        btn6 = (Button) findViewById(R.id.game4_btn6);
        btn7 = (Button) findViewById(R.id.game4_btn7);
        btn8 = (Button) findViewById(R.id.game4_btn8);
        btn9 = (Button) findViewById(R.id.game4_btn9);
        btn10 = (Button) findViewById(R.id.game4_btn10);
        btn11 = (Button) findViewById(R.id.game4_btn11);
        btn12 = (Button) findViewById(R.id.game4_btn12);
        btn13 = (Button) findViewById(R.id.game4_btn13);
        btn14 = (Button) findViewById(R.id.game4_btn14);
        btn15 = (Button) findViewById(R.id.game4_btn15);
        btn16 = (Button) findViewById(R.id.game4_btn16);
        btn17 = (Button) findViewById(R.id.game4_btn17);
        btn18 = (Button) findViewById(R.id.game4_btn18);
        btn19 = (Button) findViewById(R.id.game4_btn19);
        btn20 = (Button) findViewById(R.id.game4_btn20);
        btn21 = (Button) findViewById(R.id.game4_btn21);
        btn22 = (Button) findViewById(R.id.game4_btn22);
        btn23 = (Button) findViewById(R.id.game4_btn23);
        btn24 = (Button) findViewById(R.id.game4_btn24);

        buttonsArray = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11,
                btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24};
        values = new ArrayList<>();
        buildGame();
    }

    private void buildGame() {
        generateValue();
        fillButton();
    }

    private void fillButton() {
        for (int i = 0; i < 24; i++) {
            buttonsArray[i].setText(values.get(i) + "");
        }
    }

    public void onClick(View view) {
        Toast.makeText(this, "click" + ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void generateValue() {
        // заповнюємо масив числами
        values.clear();
        for (int i = 0; i < bound; i++) {
            values.add(new Random().nextInt(bound));
        }
        checkIsUnique();
    }

    private void checkIsUnique() {
        for (int i = 0; i < values.size(); i++) {
            for (int j = 1 + i; j < values.size(); j++) {
                if (values.get(i) == values.get(j)) {
                    values.set(i, new Random().nextInt(bound));
                    checkIsUnique();
                }
            }
        }
    }
}
