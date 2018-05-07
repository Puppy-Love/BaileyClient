package com.min.bailey.client.mvp.ui.widget.stepview.bean;

/**
 * @author Administrator
 * @describe step 控件实体类
 */
public class StepBean {
    /**
     * -1 未完成
     * 0  正在进行
     * 1  已完成
     * @describe
     */
    public static final int STEP_UNDO = -1;
    public static final int STEP_CURRENT = 0;
    public static final int STEP_COMPLETED = 1;

    private String name;
    private int state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public StepBean() {
    }

    public StepBean(String name, int state) {
        this.name = name;
        this.state = state;
    }
}
