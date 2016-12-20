package com.yxnne.criminalintent;

import android.content.Context;

import com.yxnne.criminalintent.entity.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yxnne on 2016/12/20.
 * 封装Crime 的 list
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();

        //fake data
        for(int i = 0 ;i < 100; i++){
            Crime crime = new Crime();
            crime.setTittle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    /**
     * 根据id找到对应的Crime
     * @param id uuid
     * @return Crime
     */
    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes){
            if(crime.getId() == id){
                return crime;
            }
        }
        return null;
    }
}
