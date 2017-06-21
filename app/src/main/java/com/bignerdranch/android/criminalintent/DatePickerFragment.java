package com.bignerdranch.android.criminalintent;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by eliballislife11 on 6/20/17.
 */

public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {   // Builds an AlertDialog with a title
        return new AlertDialog.Builder(getActivity())           // and an OK button
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
