package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeCursorWrapper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable.*;

/**
 * Created by eliballislife11 on 6/7/17.
 */

public class CrimeLab {     // The model of my MVC
    private static CrimeLab sCrimeLab;  // s prefix for static methods

    //private List<Crime> mCrimes;
    private final SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {     // constructor for singleton
        mDatabase = new CrimeBaseHelper(context).getWritableDatabase();     // Opening a SQLiteDatabase
       // mCrimes = new ArrayList<>();    // Goodbye random crimes!
    }

    public void addCrime(Crime c) {
       // mCrimes.add(c);
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }

    public List<Crime> getCrimes() {        // Returning the crime list
       // return mCrimes;
        //return new ArrayList<>();
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {
       /* for(Crime crime: mCrimes) {
            if(crime.getId().equals(id)) {
                return crime;
            }
        }*/
        //return null;

        CrimeCursorWrapper cursor = queryCrimes(
                Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {              // Updating a crime
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                Cols.UUID + " = ?", new String[]{ uuidString });
    }

    private static ContentValues getContentValues(Crime crime) {    // Use ContentValues to assist with
        ContentValues values = new ContentValues();                 // writing and updating databases
        values.put(Cols.UUID, crime.getId().toString());
        values.put(Cols.TITLE, crime.getTitle());
        values.put(Cols.DATE, crime.getDate().getTime());
        values.put(Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(Cols.SUSPECT, crime.getSuspect());

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {        // Quering Crimes
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,   // Columns - null selects all columns
                whereClause,
                whereArgs,
                null,   // groupBy
                null,   // having
                null  // orderBy
        );
        return new CrimeCursorWrapper(cursor);
    }
}
