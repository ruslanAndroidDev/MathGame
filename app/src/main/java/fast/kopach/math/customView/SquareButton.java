package fast.kopach.math.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Button;

import fast.kopach.math.R;

/**
 * Created by Руслан on 13.09.2017.
 */

public class SquareButton extends Button {

    public SquareButton(Context context) {
        super(context);
    }

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setColor(int color) {
        Drawable state_normal = getResources().getDrawable(R.drawable.round_button).mutate();
        state_normal.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        Drawable state_pressed = getResources().getDrawable(R.drawable.round_button).mutate();
        state_pressed.setColorFilter(ContextCompat.getColor(getContext(), R.color.game2Pressed), PorterDuff.Mode.SRC_ATOP);

        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed}, state_pressed);
        states.addState(new int[]{android.R.attr.state_selected}, state_pressed);

        states.addState(new int[]{android.R.attr.state_enabled}, state_normal);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(states);
        } else {
            setBackgroundDrawable(states);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        Drawable state_pressed = getResources().getDrawable(R.drawable.round_button).mutate();
        state_pressed.setColorFilter(ContextCompat.getColor(getContext(), R.color.game2Pressed), PorterDuff.Mode.SRC_ATOP);

        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed}, state_pressed);
        states.addState(new int[]{android.R.attr.state_selected}, state_pressed);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(states);
        } else {
            setBackgroundDrawable(states);
        }
    }
}
