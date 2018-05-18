package com.memeinfotech.tesla1way;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class AirportRide extends AppCompatActivity {
    public EditText nameText1;
    static TextView displayTime;
    static TextView displayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport__ride);
        displayTime = (TextView)findViewById(R.id.selected_time);
        displayDate=(TextView)findViewById(R.id.selected_date);
        this.nameText1=(EditText)findViewById(R.id.nameText1);
        nameText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'

                    selectTime(null);

                    return true;

                }
                return false;
            }
        });

    }

    public void selectTime(View v) {
        TimePicker mTimePicker = new TimePicker();

        mTimePicker.show(getFragmentManager(), "Select time");
    }

    public void selectDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public void nextClick(View view)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            displayTime.setText(String.valueOf(hourOfDay) + " : " + String.valueOf(minute));
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            displayDate.setText(String.valueOf(day) + " - " + String.valueOf(month) + " - " + String.valueOf(year));
        }
    }

    public void BookingrideClick(View view) {
    }
}
