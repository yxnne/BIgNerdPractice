package com.yxnne.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yxnne.criminalintent.db.CrimeBaseHelper;
import com.yxnne.criminalintent.db.CrimeCursorWrapper;
import com.yxnne.criminalintent.entity.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.yxnne.criminalintent.db.CrimeDBSchema.CrimeTable;

/**
 * Created by yxnne on 2016/12/20.
 * 封装Crime 的 list
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    //private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatebase;

    private CrimeLab(Context context){
        //mCrimes = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDatebase = new CrimeBaseHelper(mContext).getWritableDatabase();
        //fake data
        //genFakeData();

    }


    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }




    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return crimes;
    }

    /**
     * 根据id找到对应的Crime
     * @param id uuid
     * @return Crime
     */
    public Crime getCrime(UUID id){
//        for(Crime crime : mCrimes){
//            if(crime.getId().equals(id)){
//                return crime;
//            }
//        }
        CrimeCursorWrapper cursor = queryCrimes(
            CrimeTable.Cols.UUID + " =?",
                new String[]{id.toString()}
        );

        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();

        }finally {
            cursor.close();
        }

    }

    /**
     * 添加一个Crime
     * @param c 添加c到list mCrimes里面
     */
    public void addCrime(Crime c){
        //mCrimes.add(c);
        //插入数据库
        ContentValues values = getContentValues(c);
        mDatebase.insert(CrimeTable.NAME,null,values);

    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues cv = new ContentValues();
        cv.put(CrimeTable.Cols.UUID,crime.getId().toString());
        cv.put(CrimeTable.Cols.TITTLE,crime.getTittle());
        cv.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        cv.put(CrimeTable.Cols.SOVLED,crime.isSolved());
        return  cv;
    }

    /**
     * 更新数据
     */
    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatebase.update(
                CrimeTable.NAME,values,CrimeTable.Cols.UUID + "=?",
                new String[]{uuidString}
        );
    }
    /**
     * 查询crime记录
     */
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatebase.query(
                CrimeTable.NAME,
                null,//  all columns
                whereClause,
                whereArgs,
                null,//groupby
                null,//having
                null //orderby
                );
        return new CrimeCursorWrapper(cursor);
    }
}
