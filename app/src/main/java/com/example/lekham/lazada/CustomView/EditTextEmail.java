package com.example.lekham.lazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/3/2017.
 */

@SuppressLint("AppCompatCustomView")
public class EditTextEmail extends EditText {
    Drawable mClearImages, mNoneClearImages;
    Drawable drawableCLear;
    Boolean mVisible = false;
    int ALPHA = (int) (255 * .20f);

    public EditTextEmail(Context context) {
        super(context);
        init();
    }

    public EditTextEmail(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextEmail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditTextEmail(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mClearImages = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        mNoneClearImages = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        setUp();
    }

    private void setUp() {
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
        Drawable[] drawable = getCompoundDrawables();
        drawableCLear = mVisible ? mClearImages : mNoneClearImages;
        if (mVisible)
            drawableCLear.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawable[0], drawable[1], drawableCLear, drawable[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        Log.d("AAA", "start :" + start + "- lengthBefore:" + lengthBefore + "-lengthAfter:" + lengthAfter);
        if (lengthAfter == 0 && start == 0) {
            mVisible = false;
            setUp();
        } else {
            mVisible = true;
            setUp();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawableCLear.getBounds().width())) {
            setText("");
        }

        return super.onTouchEvent(event);
    }
}
