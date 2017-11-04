package fast.kopach.math.customView;

import android.graphics.Rect;

/**
 * Created by Руслан on 29.10.2017.
 */

class PuzzleAnswer {
    int answer;
    Rect position;
    Rect defaultPosition;
    PuzzleQuestion atachedTo;
    public PuzzleGameView.PuzzleType type;

    public PuzzleAnswer(Rect position, int answer,Rect defaultPosition) {
        this.position = position;
        this.answer = answer;
        this.defaultPosition = defaultPosition;
    }

    public void attachTo(PuzzleQuestion puzzle) {
        atachedTo = puzzle;
        position.left = puzzle.position.right - 50;
        position.top = puzzle.position.top;
        position.bottom = puzzle.position.bottom;
        position.right = puzzle.position.right + PuzzleGameView.PUZZLE_ANSWER_WIDTH - 50;
    }
}