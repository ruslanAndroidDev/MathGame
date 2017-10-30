package fast.kopach.math.customView;

import android.graphics.Rect;

/**
 * Created by Руслан on 29.10.2017.
 */

class Puzzle {
    String text;
    Rect position;
    PuzzleGameView.PuzzleType type;
    Puzzle atachedPuzzle;

    public Puzzle(Rect position, PuzzleGameView.PuzzleType type, String text) {
        this.position = position;
        this.type = type;
        this.text = text;
    }

    public void attachTo(Puzzle puzzle) {
        atachedPuzzle = puzzle;
        position.left = puzzle.position.right-50;
        position.top = puzzle.position.top;
        position.bottom = puzzle.position.bottom;
        position.right = puzzle.position.right + PuzzleGameView.PUZZLE_ANSWER_WIDTH-50;
    }
}