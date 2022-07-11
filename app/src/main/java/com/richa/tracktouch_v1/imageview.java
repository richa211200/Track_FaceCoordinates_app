package com.richa.tracktouch_v1;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class imageview extends androidx.appcompat.widget.AppCompatImageView {

    Paint paint;



    public imageview(Context context) {
        super(context);

        init();
    }

    private void init() {
        paint = new Paint();

        paint.setAntiAlias(true);
    }

    public imageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public imageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public imageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: Imageview dispatchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: Imageview dispatchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: Imageview dispatchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: Imageview dispatchEvent CANCEL");
                break;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"Imageview dispatchEvent RETURNS"+b);

        return b;
    }

    public boolean onInterceptTouchEvent(@NonNull MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, " Imageview onInterceptTouchEvent DOWN");
                return true;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, " Imageview onInterceptTouchEvent MOVE");
                return true;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, " Imageview onInterceptTouchEvent UP");
                return true;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, " Imageview onInterceptTouchEvent CANCEL");
                return true;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"Imageview onInterceptTouchEvent RETURNS"+b);

        return b;
    }


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {

        switch (ev.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, " Imageview onTouchEvent DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, " Imageview onTouchEvent MOVE");
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, " Imageview onTouchEvent UP");
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, " Imageview onTouchEvent CANCEL");
                break;

        }

        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG,"Imageview onTouchEvent RETURNS"+b);

        return b;
    }

    //2:33
}

