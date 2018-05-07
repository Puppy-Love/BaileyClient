package com.min.bailey.client.mvp.ui.widget.tailoredittext;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author Administrator
 */
public class DeTextChangeListener implements TextWatcher {

    private DeView mDEView;

    public DeTextChangeListener(DeView DEView) {
        mDEView = DEView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String text = mDEView.getText().toString();
        if (text.isEmpty()) {
            mDEView.hideDelete();
        } else {
            mDEView.showDelete();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
