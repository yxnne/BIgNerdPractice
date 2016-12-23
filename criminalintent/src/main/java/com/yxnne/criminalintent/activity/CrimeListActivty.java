package com.yxnne.criminalintent.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.yxnne.criminalintent.R;
import com.yxnne.criminalintent.entity.Crime;
import com.yxnne.criminalintent.fragment.CrimeFragment;
import com.yxnne.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivty extends SingleFragmentActivity
    implements CrimeListFragment.Callbacks,CrimeFragment.Callbacks{

    @Override
    protected Fragment creteFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        //这里的思路
        //若用户使用手机页面布局：启动新的activity
        //若使用平板页面布局，使用Fragment放到右边

        if(findViewById(R.id.fragment_detail_container )== null){
            //手机布局
            Intent intent = CrimePagerActivity.newIntent(this,crime.getId());
            startActivity(intent);
        }else{
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail_container,newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
