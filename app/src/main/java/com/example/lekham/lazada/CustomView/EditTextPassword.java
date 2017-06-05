package com.example.lekham.lazada.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.lekham.lazada.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Le Kham on 6/2/2017.
 */


@SuppressLint("AppCompatCustomView")
public class EditTextPassword extends EditText implements View.OnFocusChangeListener {
    Drawable drawable;
    Drawable mEye, mEyeStrike;
    boolean mVisible = false;
    boolean mUseStrike = false;
    boolean mUserError = false;
    int mTextSizeMin = 0;
    int mTextSizeMax = 0;
    String mTextErrorEmpty;
    String mTextErrorSizeMinInvalid;
    String mTextErrorSizeMaxInvalid;
    String mTextErrorTextInvalid;
    int ALPHA = (int) (255 * .20f);
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern mPattern;
    Matcher mMatcher;
    TextInputLayout mTextInputLayout;

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
            mUserError = typedArray.getBoolean(R.styleable.EditTextPassword_useErrorPass, false);
            mTextErrorEmpty = typedArray.getString(R.styleable.EditTextPassword_textErrorPassEmpty);
            mTextErrorSizeMinInvalid = typedArray.getString(R.styleable.EditTextPassword_textErrorSizeMinInvalid);
            mTextErrorSizeMaxInvalid = typedArray.getString(R.styleable.EditTextPassword_textErrorSizeMaxInvalid);
            mTextErrorTextInvalid = typedArray.getString(R.styleable.EditTextPassword_textErrorTextInvalid);
            mTextSizeMin = typedArray.getInt(R.styleable.EditTextPassword_textSizeMin, 0);
            mTextSizeMax = typedArray.getInt(R.styleable.EditTextPassword_textSizeMax, 0);
        }
        mEye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        mEyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        setUp();
        this.setOnFocusChangeListener(this);

    }

    private void setUp() {
        mPattern = Pattern.compile(MATCHER_PATTERN);
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (mUserError && !TextUtils.isEmpty(mTextErrorEmpty)) {
            mTextInputLayout = (TextInputLayout) v.getParent().getParent();
            if (!hasFocus) {
                String text = getText().toString();
                if (TextUtils.isEmpty(text)) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextInputLayout.setError(mTextErrorEmpty);
                }
            }
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (mUserError && mTextInputLayout != null && mTextSizeMax > 0
                && mTextSizeMin > 0 && !TextUtils.isEmpty(mTextErrorSizeMaxInvalid)
                && !TextUtils.isEmpty(mTextErrorSizeMinInvalid) && !TextUtils.isEmpty(mTextErrorTextInvalid)
                && !TextUtils.isEmpty(mTextErrorEmpty)) {
            if (lengthAfter != lengthBefore) {
                if (start <= 0 && lengthBefore != 0) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextInputLayout.setError(mTextErrorEmpty);
                } else if (start < mTextSizeMin) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextErrorSizeMinInvalid = mTextErrorSizeMinInvalid.replace("%", String.valueOf(mTextSizeMin));
                    mTextInputLayout.setError(mTextErrorSizeMinInvalid);
                } else if (start > mTextSizeMax) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextErrorSizeMaxInvalid = mTextErrorSizeMaxInvalid.replace("%", String.valueOf(mTextSizeMax));
                    mTextInputLayout.setError(mTextErrorSizeMaxInvalid);
                } else if (start >= mTextSizeMin && start <= mTextSizeMax) {
                    mMatcher = mPattern.matcher(text);
                    if (!mMatcher.matches()) {
                        mTextInputLayout.setErrorEnabled(true);
                        mTextInputLayout.setError(mTextErrorTextInvalid);
                    } else {
                        mTextInputLayout.setErrorEnabled(false);
                        mTextInputLayout.setError(null);
                    }
                }
            }
        }
    }
}
