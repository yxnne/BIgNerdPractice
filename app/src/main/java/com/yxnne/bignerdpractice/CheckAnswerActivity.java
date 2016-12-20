package com.yxnne.bignerdpractice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class CheckAnswerActivity extends AppCompatActivity {
    private Button mBtnSure;
    private TextView mTvAnswer;

    private static String EXTRA_ANSWER_IS = "CheckAnswerActivity.answer";
    public static String EXTRA_ANSWER_SHOWN = "CheckAnswerActivity.answershown";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answer);
        Intent i = getIntent();
        final boolean answer = i.getBooleanExtra(EXTRA_ANSWER_IS,false);
        mTvAnswer = (TextView) findViewById(R.id.tv_answer);
        mBtnSure = (Button) findViewById(R.id.btn_sure);
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer){
                    mTvAnswer.setText(R.string.answer_right);
                }else {
                    mTvAnswer.setText(R.string.answer_wrong);
                }
                setAnswerShowResault(true);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    int cx = mBtnSure.getWidth() /2;
                    int cy = mBtnSure.getHeight() /2;
                    float radius = mBtnSure.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(mBtnSure,cx,cy,radius,0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mBtnSure.setVisibility(View.INVISIBLE);
                        }
                    });

                    anim.start();
                }else{
                    mBtnSure.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void setAnswerShowResault(boolean isShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isShown);
        setResult(RESULT_OK,data);
    }

    public static Intent getStartMeIntent(Context context,boolean answer){
        Intent i = new Intent(context,CheckAnswerActivity.class);
        i.putExtra(EXTRA_ANSWER_IS,answer);
        return i;

    }
}
