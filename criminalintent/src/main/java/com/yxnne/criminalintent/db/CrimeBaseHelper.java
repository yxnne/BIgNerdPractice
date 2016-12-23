package com.yxnne.criminalintent.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yxnne.criminalintent.db.CrimeDBSchema.CrimeTable;

/**
 * Created by yxnne on 2016/12/22.
 * SQLiteOpenHelper
 */

public class CrimeBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String  DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME,
                null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ CrimeTable.NAME + "("
                + "_id integer primary key autoincrement,"
                + CrimeTable.Cols.UUID + ","
                + CrimeTable.Cols.TITTLE + ","
                + CrimeTable.Cols.DATE + ","
                + CrimeTable.Cols.SOVLED + ","
                + CrimeTable.Cols.SUSPECT
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
