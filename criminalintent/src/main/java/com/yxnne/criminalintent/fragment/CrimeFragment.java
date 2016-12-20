package com.yxnne.criminalintent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yxnne.criminalintent.R;
import com.yxnne.criminalintent.entity.Crime;

/**
 * Created by yxnne on 2016/12/20.
 *
 */

public class CrimeFragment extends Fragment{
    /**持有一个模型层对象*/
    private Crime mCrime;

    private EditText mTittleField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_crime,container,false);

        mTittleField = (EditText) v.findViewById(R.id.crime_tittle);
        mTittleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //给模型层赋值
                mCrime.setTittle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;

    }
}
