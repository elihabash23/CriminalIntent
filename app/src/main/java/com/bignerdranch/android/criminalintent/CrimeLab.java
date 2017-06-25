package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by eliballislife11 on 6/7/17.
 */

public class CrimeLab {     // The model of my MVC
    private static CrimeLab sCrimeLab;  // s prefix for static methods

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {     // constructor for singleton
        mCrimes = new ArrayList<>();    // Goodbye random crimes!
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for(Crime crime: mCrimes) {
            if(crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
