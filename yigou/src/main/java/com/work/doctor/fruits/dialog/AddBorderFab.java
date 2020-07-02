package com.work.doctor.fruits.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddBorderFab extends FloatingActionButton {

    private static final int borderWidth = 1;    //边框的宽度

    Paint  paint;
    Canvas canvas;

    public AddBorderFab(Context context) {
        super(context);
        initView();
    }

    public AddBorderFab(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AddBorderFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        canvas = new Canvas();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#c0c3c5"));
        paint.setStrokeWidth((float) borderWidth);
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2
                - borderWidth, paint);

        canvas.save();
        super.onDraw(canvas);
        canvas.restore();
    }
}
