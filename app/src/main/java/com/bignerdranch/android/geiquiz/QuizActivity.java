package com.bignerdranch.android.geiquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.geiquiz.R;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "QuizActivity";
    private static String KEY_INDEX = "index";
    private static String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geiquiz.answer_is_true";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;

    private TextView mQuestionTextViews;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_oceans,true),
        new Question(R.string.question_mideast,false),
        new Question(R.string.question_africa,false),
        new Question(R.string.question_americas,true),
        new Question(R.string.question_asia,true),
    };

    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextViews = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mCheatButton.setOnClickListener(this);

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        updateQuestion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                mCurrentIndex = (mCurrentIndex + 1) % (mQuestionBank.length);
                Log.i("GeoQuiz","mCurrentIndex = " + mCurrentIndex);
                updateQuestion();
                break;
            case R.id.cheat_button:
                Log.i(TAG, "onClick: cheat_button!");
                Intent i = newIntent(this,mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivity(i);
                break;
            default:
                break;
        }
    }
    private static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextViews.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = R.string.correct_toast;

        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }
}
