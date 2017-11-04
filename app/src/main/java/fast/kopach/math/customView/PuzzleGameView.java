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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import fast.kopach.math.R;

/**
 * Created by Руслан on 28.10.2017.
 */

public class PuzzleGameView extends View implements View.OnTouchListener {
    Paint myPaint;

    static int PUZZLE_ANSWER_WIDTH;
    int PUZZLE_QUESTION_WIDTH;
    int PUZZLE_HEIGHT;

    ArrayList<PuzzleAnswer> puzzleAnswers;
    ArrayList<PuzzleQuestion> puzzleQuestions;

    private int touchPazzle = -1;

    Rect answerSrcRect;
    Rect questionsSrcRect;
    Rect OverlapsSrcRect;

    int PuzzleCenterHeght;
    int PuzzleCenterWidth;

    Random random;
    ArrayList<Integer> answers;
    private PuzzlTask[] puzzlTasks;
    private int startPosition;
    private int score = 0;

    private void initPaint() {
        myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setColor(Color.WHITE);
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
        questionsSrcRect = new Rect(0, 0, bitmapNorm.getWidth(), bitmapNorm.getHeight());
        answerSrcRect = new Rect(0, 0, bitmapAnswer.getWidth(), bitmapAnswer.getHeight());
        OverlapsSrcRect = new Rect(0, 0, bitmapOverlaps.getWidth(), bitmapOverlaps.getHeight());
        random = new Random();
        puzzleQuestions = new ArrayList<>();
        puzzleAnswers = new ArrayList<>();

        puzzlTasks = new PuzzlTask[4];
        answers = new ArrayList<>();

        initPaint();
        generateExample();

        setOnTouchListener(this);
    }

    enum PuzzleType {
        PUZZLE_ANSWER, PUZZLE_OVERLAPS

    }

    public void buildGame(int score) {
        this.score = score;
        generateExample();
        puzzleAnswers.clear();
        puzzleQuestions.clear();
        Collections.shuffle(answers);

        for (int i = 0; i < 5; i++) {
            Rect position = new Rect(startPosition, 10 + i * (PUZZLE_HEIGHT + 20), startPosition + PUZZLE_ANSWER_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            Rect defaultPosition = new Rect(startPosition, 10 + i * (PUZZLE_HEIGHT + 20), startPosition + PUZZLE_ANSWER_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            puzzleAnswers.add(new PuzzleAnswer(position, answers.get(i), defaultPosition));
        }
        for (int i = 0; i < 4; i++) {
            Rect position = new Rect(10, 10 + i * (PUZZLE_HEIGHT + 20), 10 + PUZZLE_QUESTION_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            puzzleQuestions.add(new PuzzleQuestion(position, puzzlTasks[i].trueVariant, puzzlTasks[i].example));
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        PUZZLE_HEIGHT = MeasureSpec.getSize(heightMeasureSpec) / 6;
        PUZZLE_ANSWER_WIDTH = MeasureSpec.getSize(widthMeasureSpec) / 4;
        PUZZLE_QUESTION_WIDTH = MeasureSpec.getSize(widthMeasureSpec) / 2 - 20;

        myPaint.setTextSize(PUZZLE_HEIGHT / 3);
        puzzleAnswers.clear();
        puzzleQuestions.clear();

        PuzzleCenterHeght = PUZZLE_HEIGHT / 2;
        PuzzleCenterWidth = PUZZLE_ANSWER_WIDTH / 2;

        startPosition = MeasureSpec.getSize(widthMeasureSpec) - PUZZLE_ANSWER_WIDTH - 10;
        for (int i = 0; i < 5; i++) {
            Rect position = new Rect(startPosition, 10 + i * (PUZZLE_HEIGHT + 20), startPosition + PUZZLE_ANSWER_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            Rect defaultPosition = new Rect(startPosition, 10 + i * (PUZZLE_HEIGHT + 20), startPosition + PUZZLE_ANSWER_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            puzzleAnswers.add(new PuzzleAnswer(position, answers.get(i), defaultPosition));
        }
        for (int i = 0; i < 4; i++) {
            Rect position = new Rect(10, 10 + i * (PUZZLE_HEIGHT + 20), 10 + PUZZLE_QUESTION_WIDTH, 10 + i * (PUZZLE_HEIGHT + 20) + PUZZLE_HEIGHT);
            puzzleQuestions.add(new PuzzleQuestion(position, puzzlTasks[i].trueVariant, puzzlTasks[i].example));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth;
        for (int i = 0; i < 4; i++) {
            PuzzleQuestion puzzle = puzzleQuestions.get(i);
            canvas.drawBitmap(bitmapNorm, questionsSrcRect, puzzle.position, myPaint);
            textWidth = myPaint.measureText(puzzle.example);
            canvas.drawText(puzzle.example, puzzle.position.centerX() - textWidth / 2, puzzle.position.centerY() + myPaint.getTextSize() / 3, myPaint);
        }
        for (int i = 0; i < 5; i++) {
            PuzzleAnswer puzzle = puzzleAnswers.get(i);
            if (puzzle.type == PuzzleType.PUZZLE_OVERLAPS) {
                canvas.drawBitmap(bitmapOverlaps, OverlapsSrcRect, puzzle.position, myPaint);
            } else {
                canvas.drawBitmap(bitmapAnswer, answerSrcRect, puzzle.position, myPaint);
            }
            textWidth = myPaint.measureText(String.valueOf(puzzle.answer));
            canvas.drawText(puzzle.answer + "", puzzle.position.centerX() - textWidth / 2, puzzle.position.centerY() + myPaint.getTextSize() / 3, myPaint);
        }
    }

    public int checkAnswer() {
        int answers = 0;
        for (int i = 0; i < 5; i++) {
            PuzzleAnswer puzzle = puzzleAnswers.get(i);
            if (puzzle.atachedTo != null) {
                if (puzzle.answer == puzzle.atachedTo.trueAnswer) {
                    answers++;
                } else {
                    return 0;
                }
            }
        }
        Toast.makeText(getContext(), "Answers:" + answers, Toast.LENGTH_SHORT).show();
        return answers;
    }

    private void generateExample() {
        answers.clear();
        for (int i = 0; i < 4; i++) {
            int znak = random.nextInt(2);
            int number1 = random.nextInt(50 + 10 * score);
            int number2 = random.nextInt(50 + 10 * score);
            PuzzlTask puzzlTask;
            if (znak == 0) {
                puzzlTask = new PuzzlTask(number1 + number2, number1 + " + " + number2 + " = ");
            } else {
                puzzlTask = new PuzzlTask(number1 - number2, number1 + " - " + number2 + " = ");
            }
            puzzlTasks[i] = puzzlTask;
            answers.add(puzzlTask.trueVariant);
        }
        answers.add(random.nextInt(50 + 10 * score));
    }

    Bitmap bitmapNorm;
    Bitmap bitmapAnswer;

    Bitmap bitmapOverlaps;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int touchX = (int) motionEvent.getX();
        int touchY = (int) motionEvent.getY();

        Rect touchRect = new Rect(touchX - 25, touchY - 25, touchX + 25, touchY + 25);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < puzzleAnswers.size(); i++) {
                    PuzzleAnswer puzzle = puzzleAnswers.get(i);
                    if (touchRect.intersect(puzzle.position)) {
                        touchPazzle = i;
                        puzzle.atachedTo = null;
                        Log.d("tag", "touchPos : " + i);
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (touchPazzle != -1) {
                    PuzzleAnswer currentPazzl = puzzleAnswers.get(touchPazzle);
                    for (int i = 0; i < puzzleQuestions.size(); i++) {
                        PuzzleQuestion puzzle = puzzleQuestions.get(i);
                        if (currentPazzl.position.intersect(puzzle.position)) {
                            currentPazzl.type = PuzzleType.PUZZLE_OVERLAPS;
                            break;
                        } else {
                            currentPazzl.type = PuzzleType.PUZZLE_ANSWER;
                        }
                    }
                    currentPazzl.position.left = touchX - PuzzleCenterWidth;
                    currentPazzl.position.top = touchY - PuzzleCenterHeght;
                    currentPazzl.position.right = touchX + PuzzleCenterWidth;
                    currentPazzl.position.bottom = touchY + PuzzleCenterHeght;
                    if (touchPazzle != 4) {
                        puzzleAnswers.remove(touchPazzle);
                        puzzleAnswers.add(4, currentPazzl);
                        touchPazzle = 4;
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (touchPazzle != -1) {
                    PuzzleAnswer currentPazzl = puzzleAnswers.get(touchPazzle);
                    currentPazzl.type = PuzzleType.PUZZLE_ANSWER;
                    puzzleAnswers.set(touchPazzle, currentPazzl);
                    for (int i = 0; i < puzzleQuestions.size(); i++) {
                        PuzzleQuestion puzzle = puzzleQuestions.get(i);
                        if (currentPazzl.position.intersect(puzzle.position)) {
                            currentPazzl.attachTo(puzzle);
                            break;
                        }
                    }

                    if (currentPazzl.atachedTo==null){
                        currentPazzl.position = new Rect(currentPazzl.defaultPosition);
                    }
                    touchPazzle = -1;
                    invalidate();
                }
                break;
        }
        return true;
    }
}
