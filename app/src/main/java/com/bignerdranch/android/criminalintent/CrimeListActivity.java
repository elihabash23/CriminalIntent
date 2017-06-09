package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by eliballislife11 on 6/7/17.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
