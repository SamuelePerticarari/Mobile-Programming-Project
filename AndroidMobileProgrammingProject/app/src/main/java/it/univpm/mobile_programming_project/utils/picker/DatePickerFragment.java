package it.univpm.mobile_programming_project.utils.picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import it.univpm.mobile_programming_project.R;

public class DatePickerFragment extends DialogFragment {

    private final DatePickerDialog.OnDateSetListener dateSetListener;

    public DatePickerFragment(DatePickerDialog.OnDateSetListener dateSetListener) {
        this.dateSetListener = dateSetListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), R.style.Theme_Widget_Dialog, this.dateSetListener, year, month, day);
    }

}
