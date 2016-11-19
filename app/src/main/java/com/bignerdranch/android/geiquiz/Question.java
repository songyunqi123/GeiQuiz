package com.bignerdranch.android.geiquiz;

/**
 * Created by songyunq123 on 2016/11/13.
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId,boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
