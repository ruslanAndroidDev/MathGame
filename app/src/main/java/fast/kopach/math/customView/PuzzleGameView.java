package fast.kopach.math.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import fast.kopach.math.R;

/**
 * Created by Руслан on 28.10.2017.
 */

public class PuzzleGameView extends View implements View.OnTouchListener {
    static int PUZZLE_ANSWER_WIDTH;
    int PUZZLE_QUESTION_WIDTH;
    ArrayList<Puzzle> puzzles;
    private int touchPazzle = -1;
    int PUZZLE_HEIGHT;
    Rect answerSrcRect;
    Rect questionsSrcRect;
    Rect OverlapsSrcRect;
    int PuzzleCenterHeght;
    int PuzzleCenterWidth;
    PuzzlTask[] puzzlTasks;
    Random random;
    ArrayList<PuzzlTask> answers;


    enum PuzzleType {
        PUZZLE_QUESTIONS, PUZZLE_ANSWER, PUZZLE_OVERLAPS
    }

    public PuzzleGameView(Context context) {
        super(context);
    }

    public PuzzleGameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmapNorm = BitmapFactory.decodeResource(getResources(), R.drawable.puzl);
        bitmapAnswer = BitmapFactory.decodeResource(getResources(), R.drawable.puzl2);
        bitmapOverlaps = BitmapFactory.decodeResource(getResources(), R.drawable.pazl2_overlaps);
        bitmapOverlaps = Bitmap.createScaledBitmap(bitmapOverlaps, (int) (bitmapOverlaps.getWidth() * 0.5),
                (int) (bitmapOverlaps.getHeight() * 0.5), true);
        myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setColor(Color.WHITE);
        questionsSrcRect = new Rect(0, 0, bitmapNorm.getWidth(), bitmapNorm.getHeight());
        answerSrcRect = new Rect(0, 0, bitmapAnswer.getWidth(), bitmapAnswer.getHeight());
        OverlapsSrcRect = new Rect(0, 0, bitmapOverlaps.getWidth(), bitmapOverlaps.getHeight());

        puzzles = new ArrayList<>();
        random = new Random();
        answers = new ArrayList<>();
        puzzlTasks = new PuzzlTask[4];
        for (int i = 0; i < 4; i++) {
            int znak = random.nextInt(2);
            int number1 = random.nextInt(50);
            int number2 = random.nextInt(50);
            PuzzlTask puzzlTask;
            if (znak == 0) {
                puzzlTask = new PuzzlTask(number1 + number2, number1 + " + " + number2 + " = ");
            } else {
                puzzlTask = new PuzzlTask(number1 - number2, number1 + " - " + number2 + " = ");
            }
            puzzlTasks[i] = puzzlTask;
            answers.add(puzzlTask);
        }
        answers.add(new PuzzlTask(random.nextInt(50), ""));
        Collections.shuffle(answers);

        setOnTouchListener(this);
    }

    Bitmap bitmapNorm;
    Bitmap bitmapAnswer;
    Bitmap bitmapOverlaps;
    Paint myPaint;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        PUZZLE_HEIGHT = MeasureSpec.getSize(heightMeasureSpec) / 6;
        PUZZLE_ANSWER_WIDTH = MeasureSpec.getSize(widthMeasureSpec) / 4;
        PUZZLE_QUESTION_WIDTH = MeasureSpec.getSize(widthMeasureSpec) / 2 - 20;

        myPaint.setTextSize(PUZZLE_HEIGHT / 3);
        puzzles.clear();

        PuzzleCenterHeght = PUZZLE_HEIGHT / 2;
        PuzzleCenterWidth = PUZZLE_ANSWER_WIDTH / 2;

        int startPosition = MeasureSpec.getSize(widthMeasureSpec) - PUZZLE_ANSWER_WIDTH - 10;
        for (int i = 0; i < 5; i++) {
            Rect position = new Rect(startPosition, 10 + i * (PUZZLE_HEIGHT + 20), startPosition + PUZZLE_ANSWER_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            puzzles.add(new Puzzle(position, PuzzleType.PUZZLE_ANSWER, String.valueOf(answers.get(i).trueVariant)));
        }
        for (int i = 0; i < 4; i++) {
            Rect position = new Rect(10, 10 + i * (PUZZLE_HEIGHT + 20), 10 + PUZZLE_QUESTION_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            puzzles.add(new Puzzle(position, PuzzleType.PUZZLE_QUESTIONS, puzzlTasks[i].example));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth;
        for (int i = puzzles.size() - 1; i >= 0; i--) {
            Puzzle puzzle = puzzles.get(i);
            if (puzzles.get(i).type == PuzzleType.PUZZLE_ANSWER) {
                canvas.drawBitmap(bitmapAnswer, answerSrcRect, puzzle.position, myPaint);
            } else if (puzzles.get(i).type == PuzzleType.PUZZLE_QUESTIONS) {
                canvas.drawBitmap(bitmapNorm, questionsSrcRect, puzzle.position, myPaint);
            } else {
                canvas.drawBitmap(bitmapOverlaps, OverlapsSrcRect, puzzle.position, myPaint);
            }
            textWidth = myPaint.measureText(puzzle.text);
            canvas.drawText(puzzle.text, puzzle.position.centerX() - textWidth / 2, puzzle.position.centerY() + myPaint.getTextSize() / 2, myPaint);
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int touchX = (int) motionEvent.getX();
        int touchY = (int) motionEvent.getY();

        Rect touchRect = new Rect(touchX - 25, touchY - 25, touchX + 25, touchY + 25);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < puzzles.size(); i++) {
                    Puzzle puzzle = puzzles.get(i);
                    if (touchRect.intersect(puzzle.position)) {
                        if (puzzle.type == PuzzleType.PUZZLE_ANSWER) {
                            touchPazzle = i;
                            Log.d("tag", "touchPos : " + i);
                            break;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                long startTime = System.nanoTime();
                if (touchPazzle != -1) {
                    Puzzle currentPazzl = puzzles.get(touchPazzle);
                    for (int i = 0; i < puzzles.size(); i++) {
                        Puzzle puzzle = puzzles.get(i);
                        if (puzzle.type == PuzzleType.PUZZLE_QUESTIONS) {
                            if (currentPazzl.position.intersect(puzzle.position)) {
                                currentPazzl.type = PuzzleType.PUZZLE_OVERLAPS;
                                break;
                            } else {
                                currentPazzl.type = PuzzleType.PUZZLE_ANSWER;
                            }
                        } else {
                            currentPazzl.type = PuzzleType.PUZZLE_ANSWER;
                        }
                    }
                    currentPazzl.position.left = touchX - PuzzleCenterWidth;
                    currentPazzl.position.top = touchY - PuzzleCenterHeght;
                    currentPazzl.position.right = touchX + PuzzleCenterWidth;
                    currentPazzl.position.bottom = touchY + PuzzleCenterHeght;
                    if (touchPazzle != 0) {
                        puzzles.remove(touchPazzle);
                        puzzles.add(0, currentPazzl);
                        touchPazzle = 0;
                    }
                    long endTime = System.nanoTime();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (touchPazzle != -1) {
                    Puzzle currentPazzl = puzzles.get(touchPazzle);
                    currentPazzl.type = PuzzleType.PUZZLE_ANSWER;
                    puzzles.set(touchPazzle, currentPazzl);
                    for (int i = 0; i < puzzles.size(); i++) {
                        Puzzle puzzle = puzzles.get(i);
                        if (puzzle.type == PuzzleType.PUZZLE_QUESTIONS) {
                            if (currentPazzl.position.intersect(puzzle.position)) {
                                currentPazzl.attachTo(puzzle);
                                break;
                            }
                        }
                    }

                    touchPazzle = -1;
                    invalidate();
                }
                break;
        }
        return true;
    }
}

