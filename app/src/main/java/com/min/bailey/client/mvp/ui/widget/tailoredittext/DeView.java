package com.min.bailey.client.mvp.ui.widget.tailoredittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.min.bailey.client.R;


/**
 * 一键清空编辑框
 * @author Administrator
 */
public class DeView extends android.support.v7.widget.AppCompatEditText {
    private boolean isListener = false;
    private Drawable mDrawable;

    public DeView(Context context) {
        this(context, null);
    }

    public DeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = getCompoundDrawables()[2];
        initListener();
        hideDelete();
    }

    private void initListener() {
        setOnFocusChangeListener(new DeFocusChangeListener(this));
        addTextChangedListener(new DeTextChangeListener(this));
    }

    /**
     * @describe 显示删除按钮并且添加监听
     */
    public void showDelete() {
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(R.drawable.ic_black_close);
        }
        final Drawable[] icons = getCompoundDrawables();
        icons[2] = mDrawable;
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setCompoundDrawables(icons[0], icons[1], icons[2], icons[3]);
        isListener = true;
    }

    /**
     * @describe 隐藏删除按钮并且删除监听
     */
    public void hideDelete() {
        final Drawable[] icons = getCompoundDrawables();
        icons[2] = null;
        setCompoundDrawables(icons[0], icons[1], icons[2], icons[3]);
        isListener = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (getCompoundDrawables()[2] == null) {
            return super.onTouchEvent(event);
        }
        setError(null);
        if (!getText().toString().isEmpty()) {
            showDelete();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isListener) {
                    //判断是否在范围内
                    if (x >= (getWidth() - getTotalPaddingRight()) && x <= (getWidth() - getPaddingRight()) && y >= getPaddingTop() && y <= getHeight() - getPaddingBottom()) {
                        //删除内容
                        setText("");
                        return true;
                    }
                } else {

                    return super.onTouchEvent(event);
                }
            default:
        }
        return super.onTouchEvent(event);
    }
}
