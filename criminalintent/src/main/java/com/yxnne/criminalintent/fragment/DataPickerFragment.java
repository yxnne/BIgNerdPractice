package com.yxnne.criminalintent.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.yxnne.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yxnne on 2016/12/21.
 * 将DatePicker封装在fragment的好处是
 * 假设旋转手机，封装在fragment中的dialog将不受影响
 */

public class DataPickerFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.yxnne.criminalintent.fragment.datepickfragment.date";


    private DatePicker mDatePicker;

    public static DataPickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);

        DataPickerFragment fragment = new DataPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //得到date对象
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        //为了给DatePicker使用
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date,null);
        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.data_picker_tittle)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year,month,day).getTime();
                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();
    }
    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment()
                .onActivityResult(
                        getTargetRequestCode(),resultCode,intent);

    }

}
