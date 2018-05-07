package com.min.bailey.client.mvp.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.min.bailey.client.R;


/**
 *
 * @author emilia
 * @time 2018/2/7 下午11:36
 */
public class CustomDialog extends Dialog {
    private ViewGroup dialogView;
    protected Context mContext;
    protected int animId;
    protected int dialogWidth;
    protected int dialogHeight;
    protected int dialogGravity;
    protected int dialogMarginX;
    protected int dialogMarginY;

    public CustomDialog(Context context) {
        this(context, R.style.custom_dialog_default);
    }
    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        dialogView = new FrameLayout(context);
        dialogView.setBackgroundColor(Color.TRANSPARENT);
        initDialog(context);
    }

    private CustomDialog(Builder b){
        this(b.mContext,b.themeId);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(b.viewWidth, b.viewHeight);
        params.bottomMargin = b.marginY;
        params.topMargin = b.marginY;
        params.leftMargin = b.marginX;
        params.rightMargin = b.marginX;
        b.contentView.setLayoutParams(params);
        dialogView.addView(b.contentView);
        animId = b.animId;
        dialogWidth = b.viewWidth;
        dialogHeight = b.viewHeight;
        dialogGravity = b.gravity;
        setCancelable(b.cancel);
        setCanceledOnTouchOutside(b.outside);
        setOnCancelListener(b.dialogCancelClick);
        setOnDismissListener(b.dialogDismissClick);
    }

    protected void initDialog(Context context){
        dialogMarginX = 0;
        dialogMarginY = 0;
        dialogGravity = Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL;
        dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWidth = WindowManager.LayoutParams.MATCH_PARENT;
        animId = -1;

    }
    protected void onCreateView(Bundle savedInstanceState){}

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dialogView);
        onCreateView(savedInstanceState);
        initWindowParam(0,0,animId);
    }

    /**设置dialog的某些属性
     * windoDeploy要放在setContenView后面，否则没有效果
     * */
    private final void initWindowParam(int x, int y,int dialog_anim) {
        Window window = getWindow(); // 得到对话框
        if(dialog_anim > 0){
            window.setWindowAnimations(dialog_anim); // 设置窗口弹出动画
        }
        WindowManager.LayoutParams wl = window.getAttributes();
        // 根据x，y坐标设置窗口需要显示的位置
        wl.x = x; // x小于0左移，大于0右移
        wl.y = y; // y小于0上移，大于0下移
        wl.gravity = dialogGravity;
        wl.width = dialogWidth;
        wl.height = dialogHeight;
        wl.horizontalMargin = dialogMarginX;
        wl.verticalMargin = dialogMarginY;
        window.setAttributes(wl);
    }
    @Override
    public View findViewById(@IdRes int id){
        return dialogView.findViewById(id);
    }
    public Resources getResources(){
        return mContext.getResources();
    }

    public final static class Builder{
        private Context mContext;
        private View contentView;
        private int themeId;
        private int animId;
        private int viewWidth;
        private int viewHeight;
        private int marginY;
        private int marginX;
        private boolean cancel = true;
        private boolean outside = true;
        private int gravity;
        private OnCancelListener dialogCancelClick;
        private OnDismissListener dialogDismissClick;

        public Builder(@NonNull Context c){
            this.mContext = c;
            viewWidth = WindowManager.LayoutParams.WRAP_CONTENT;
            viewHeight = WindowManager.LayoutParams.MATCH_PARENT;
            gravity = Gravity.CENTER_HORIZONTAL| Gravity.CENTER_VERTICAL;
            themeId = R.style.custom_dialog_default;
        }
        public Builder view(@NonNull View view){
            contentView = view;
            return this;
        }
        public Builder view(@LayoutRes int id){
            view(LayoutInflater.from(mContext).inflate(id,null));
            return this;
        }
        public Builder theme(@StyleRes int id){
            themeId = id;
            return this;
        }
        public Builder anim(@StyleRes int id){
            animId = id;
            return this;
        }
        public Builder size(int w,int h){
            this.viewWidth = w;
            this.viewHeight = h;
            if(w > 0){
                this.viewWidth = unit(w, TypedValue.COMPLEX_UNIT_DIP);
            }
            if(h > 0){
                this.viewHeight = unit(h, TypedValue.COMPLEX_UNIT_DIP);
            }
            return this;
        }
        public Builder margin(int m){
            margin(m,m);
            return this;
        }
        public Builder margin(int x,int y){
            this.marginX = unit(x, TypedValue.COMPLEX_UNIT_DIP);
            this.marginY = unit(y, TypedValue.COMPLEX_UNIT_DIP);
            return this;
        }

        public Builder cancel(boolean is){
            this.cancel = is;
            return this;
        }
        public Builder outside(boolean is){
            this.outside = is;
            return this;
        }
        public Builder gravity(int g){
            this.gravity = g;
            return this;
        }

        public Builder on(Object obj){
            if(obj instanceof OnCancelListener){
                dialogCancelClick = (OnCancelListener)obj;
            }
            if(obj instanceof OnDismissListener){
                dialogDismissClick = (OnDismissListener)obj;
            }
            return this;
        }
        public CustomDialog create(){
            return new CustomDialog(this);
        }

        private int unit(int value,int unit){
            return (int) TypedValue.applyDimension(unit, value,
                    mContext.getResources().getDisplayMetrics());
        }
    }
}
