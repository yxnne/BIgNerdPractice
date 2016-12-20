package com.yxnne.bignerdpractice.entity;

/**
 * the Question
 * Created by Administrator on 2016/11/15.
 */

public class Question {
    private int mQestionId;
    private boolean mAnswer;

    public Question(int mQestionId, boolean mAnswer) {
        this.mQestionId = mQestionId;
        this.mAnswer = mAnswer;
    }

    public int getQestionId() {
        return mQestionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setQestionId(int qestionId) {
        mQestionId = qestionId;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
