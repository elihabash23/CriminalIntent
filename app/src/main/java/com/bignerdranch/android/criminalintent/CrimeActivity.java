package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);   // Retrieve the CrimeFragment
                                                                            // from the FragmentManager

        // The FragmentTransaction class uses a fluent interface - methods that
        // configure FragmentTransaction return a Fragment Transaction instead
        // of void, which allows you to chain them together. The code says
        // "Create a new fragment transaction, include on add operation in it,
        // and then commit it."

        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)     // Creates a fragment transaction
                    .commit();
        }
    }

}
