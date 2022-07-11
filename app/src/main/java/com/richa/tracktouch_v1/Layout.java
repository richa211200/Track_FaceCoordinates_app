package com.richa.tracktouch_v1;

import static android.content.ContentValues.TAG;
import static android.view.KeyEvent.ACTION_DOWN;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Layout extends FrameLayout {

    Paint paint = null;

    public Layout( Context context) {
        super(context);
        init();
    }

    private void init() {

        paint = new Paint();

        paint.setAntiAlias(true);
        setWillNotDraw(false);
    }

    public Layout(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Layout( Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public Layout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: MyLayout dispatchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: MyLayout dispatchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: MyLayout dispatchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: MyLayout dispatchEvent CANCEL");
                break;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"MyLayout dispatchEvent RETURNS"+b);

        return b;
    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull MotionEvent ev) {
        // If you want your layout to handle the touch event return 'true' in this else return 'false'
        // this by default returns false
        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, " MyLayout onInterceptTouchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, " MyLayout onInterceptTouchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, " MyLayout onInterceptTouchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, " MyLayout onInterceptTouchEvent CANCEL");
                break;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"MyLayout onInterceptTouchEvent RETURNS"+b);

        return b;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, " MyLayout onTouchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, " MyLayout onTouchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, " MyLayout onTouchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, " MyLayout onTouchEvent CANCEL");
                break;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"MyLayout onTouchEvent RETURNS"+b);

        return b;
    }

    //2:02
}
