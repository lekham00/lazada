package com.example.lekham.lazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/2/2017.
 */


@SuppressLint("AppCompatCustomView")
public class EditTextPassword extends EditText {
    Drawable drawable;
    Drawable mEye, mEyeStrike;
    Boolean mVisible = false;
    Boolean mUseStrike = false;
    int ALPHA = (int) (255 * .20f);

    public EditTextPassword(Context context) {
        super(context);
        init(null);
    }

    public EditTextPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EditTextPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditTextPassword(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextPassword, 0, 0);
            mUseStrike = typedArray.getBoolean(R.styleable.EditTextPassword_useStrike, false);
        }
        mEye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        mEyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        setUp();
    }

    private void setUp() {

        setInputType(InputType.TYPE_CLASS_TEXT | (mVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = mUseStrike && !mVisible ? mEyeStrike : mEye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())) {
            mVisible = !mVisible;
            setUp();
            invalidate();
        }

        return super.onTouchEvent(event);
    }
}
