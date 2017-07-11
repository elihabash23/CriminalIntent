package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by eliballislife11 on 6/7/17.
 */

public class CrimeListActivity extends SingleFragmentActivity {     // A controller of my MVC

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_twopane;
    }
}
