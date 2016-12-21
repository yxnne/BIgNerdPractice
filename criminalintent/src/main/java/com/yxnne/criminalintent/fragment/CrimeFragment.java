package com.yxnne.criminalintent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.yxnne.criminalintent.CrimeLab;
import com.yxnne.criminalintent.R;
import com.yxnne.criminalintent.entity.Crime;

import java.util.UUID;

/**
 * Created by yxnne on 2016/12/20.
 *
 */

public class CrimeFragment extends Fragment{

    private static final String ARG_CRIME_ID = "crime_id";
    /**持有一个模型层对象*/
    private Crime mCrime;

    private EditText mTittleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    /**
     * 创建本Fragment使用静态方法，解掉和Activity的耦合
     * @param crimeId UUID
     * @return this Fragment instance
     */
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

        //直接获取activity的intent 参数，但是这样会破坏封装性
        //就是成了由某一个特殊的activity托管的了
        //所以使用静态方法创建对象比较好
        //UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_crime,container,false);

        mTittleField = (EditText) v.findViewById(R.id.crime_tittle);
        mTittleField.setText(mCrime.getTittle());
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

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton .setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        return v;

    }
}
