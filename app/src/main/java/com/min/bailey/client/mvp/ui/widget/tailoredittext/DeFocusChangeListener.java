package com.min.bailey.client.mvp.ui.widget.tailoredittext;

import android.view.View;


/**
 * @author Administrator
 */
public class DeFocusChangeListener implements View.OnFocusChangeListener {

    private DeView mDEView;

    public DeFocusChangeListener(DeView DEView) {
        mDEView = DEView;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (!mDEView.getText().toString().isEmpty()) {
                mDEView.showDelete();
            } else {
                mDEView.hideDelete();
            }
        } else {
            mDEView.hideDelete();
        }
    }
}
