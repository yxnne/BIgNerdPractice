package com.yxnne.criminalintent.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.yxnne.criminalintent.entity.Crime;

import java.util.Date;
import java.util.UUID;

import static com.yxnne.criminalintent.db.CrimeDBSchema.*;

/**
 * Created by yxnne on 2016/12/22.
 * CursorWrapper
 */

public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String tittle = getString(getColumnIndex(CrimeTable.Cols.TITTLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOVLED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTittle(tittle);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
