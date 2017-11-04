package fast.kopach.math.customView;

import android.graphics.Rect;

/**
 * Created by Руслан on 03.11.2017.
 */

public class PuzzleQuestion {
    int trueAnswer;
    Rect position;
    PuzzleQuestion atachedTo;
    String example;

    public PuzzleQuestion(Rect position, int trueAnswer, String example) {
        this.position = position;
        this.trueAnswer = trueAnswer;
        this.example = example;
    }
}
