package com.yxnne.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.yxnne.criminalintent.fragment.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment creteFragment() {
        return new CrimeFragment();
    }
}
