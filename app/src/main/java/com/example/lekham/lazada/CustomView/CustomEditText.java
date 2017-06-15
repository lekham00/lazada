package com.example.lekham.lazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.icu.util.MeasureUnit;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.lekham.lazada.R;

import java.util.regex.Pattern;

/**
 * Created by Le Kham on 6/3/2017.
 */

@SuppressLint("AppCompatCustomView")
public class CustomEditText extends EditText implements View.OnFocusChangeListener {
    Drawable mClearImages, mNoneClearImages;
    Drawable drawableCLear;
    Boolean mVisible = false;
    boolean mUseEmail = false;
    boolean mUseError = false;
    String mTextErrorEmpty;
    String mTextErrorEmailInvalid;
    int ALPHA = (int) (255 * .20f);
    TextInputLayout mTextInputLayout;

    public CustomEditText(Context context) {
        super(context);
        init(null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.CustomEditText, 0, 0);
            mUseEmail = typedArray.getBoolean(R.styleable.CustomEditText_useEmail, false);
            mUseError = typedArray.getBoolean(R.styleable.CustomEditText_useError, false);
            mTextErrorEmpty = typedArray.getString(R.styleable.CustomEditText_textErrorEmpty);
            mTextErrorEmailInvalid = typedArray.getString(R.styleable.CustomEditText_textErrorEmailInvalid);
        }
        mClearImages = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        mNoneClearImages = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        setUp();
        this.setOnFocusChangeListener(this);
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
        handelCloseButton(start, lengthAfter);
        handelError(text.length(), lengthBefore, text);

    }

    private void handelCloseButton(int start, int lengthAfter) {
        if (lengthAfter == 0 && start == 0) {
            mVisible = false;
            setUp();
        } else {
            mVisible = true;
            setUp();
        }
    }

    private void handelError(int start, int lengthBefore, CharSequence text) {
        if (mUseError && !TextUtils.isEmpty(mTextErrorEmpty)) {
            if (start <= 0 && lengthBefore != 0) {
                mTextInputLayout.setErrorEnabled(true);
                mTextInputLayout.setError(mTextErrorEmpty);
            } else if (mUseEmail && !TextUtils.isEmpty(mTextErrorEmailInvalid)) {
                boolean isCheckEmail = Patterns.EMAIL_ADDRESS.matcher(text).matches();
                if (!isCheckEmail) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextInputLayout.setError(mTextErrorEmailInvalid);
                } else {
                    mTextInputLayout.setErrorEnabled(false);
                    mTextInputLayout.setError(null);
                }
            } else {
                mTextInputLayout.setErrorEnabled(false);
                mTextInputLayout.setError(null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawableCLear.getBounds().width())) {
            setText("");
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (mUseError && !TextUtils.isEmpty(mTextErrorEmpty)) {
            mTextInputLayout = (TextInputLayout) v.getParent().getParent();
            if (!hasFocus) {
                if (TextUtils.isEmpty(getText())) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextInputLayout.setError(mTextErrorEmpty);
                }
            }
        }
    }
}
