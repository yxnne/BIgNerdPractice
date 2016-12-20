package com.yxnne.bignerdpractice;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yxnne.bignerdpractice.entity.Question;

public class MainActivity extends AppCompatActivity {
    private TextView mTvQuestion;
    private Button mBtnNext;
    private Button mBtnCheat;
    private Button mBtnTure;
    private Button mBtnFalse;
    private int mCurrentIndex;
    private static String KEY_INDEX = "index";
    private static int REQ_CODE = 2001;
    private Question[] mQuestions = new Question[]{
       new Question(R.string.qustion_1,true),
       new Question(R.string.qustion_2,false),
       new Question(R.string.qustion_3,true),
       new Question(R.string.qustion_4,true),
    } ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        findViews();
        updateCurrentQustion();
        setListner();
    }

    private void updateCurrentQustion() {
        //Log.d("yxnne","updateCurrentQustion",new Exception());
        mTvQuestion.setText(mQuestions[mCurrentIndex].getQestionId());
    }

    private void setListner() {
        mBtnTure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
            }
        });
        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestions.length;
                updateCurrentQustion();
            }
        });
        mBtnCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult();
//                startActivity(
//                        CheckAnswerActivity.getStartMeIntent(MainActivity.this,mQuestions[mCurrentIndex].isAnswer()));
                Intent i = CheckAnswerActivity.getStartMeIntent(MainActivity.this,
                        mQuestions[mCurrentIndex].isAnswer());
                startActivityForResult(i,REQ_CODE);

            }
        });
    }

    private void checkAnswer(boolean b) {
        Question q  = mQuestions[mCurrentIndex];
        String strToast = getResources().getString(R.string.toast_right);
        if(q.isAnswer()){
            if(!b){
                strToast = getResources().getString(R.string.toast_wrong);
            }
        }else{
            if(b){
                strToast = getResources().getString(R.string.toast_wrong);
            }
        }
        Toast.makeText(getApplicationContext(),strToast,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= RESULT_OK){
            return;
        }
        if(requestCode == REQ_CODE){
            if(null == data){
                return;
            }
            if(data.getExtras().getBoolean(CheckAnswerActivity.EXTRA_ANSWER_SHOWN,false)){
                Toast.makeText(getApplicationContext(),R.string.you_are_cheater,Toast.LENGTH_SHORT).show();
            }
            //data.getExtras().getBoolean(CheckAnswerActivity.EXTRA_ANSWER_SHOWN,false);
        }
    }

    private void findViews() {
        mBtnTure = (Button) findViewById(R.id.btn_true);
        mBtnFalse = (Button) findViewById(R.id.btn_false);
        mBtnNext = (Button) findViewById(R.id.btn_next_question);
        mBtnCheat = (Button) findViewById(R.id.btn_see_answer);
        mTvQuestion = (TextView) findViewById(R.id.tv_qestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }
}
