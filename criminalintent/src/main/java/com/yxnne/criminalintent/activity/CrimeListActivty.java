package com.yxnne.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.yxnne.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivty extends SingleFragmentActivity {

    @Override
    protected Fragment creteFragment() {
        return new CrimeListFragment();
    }
}
