package com.memeinfotech.tesla1way;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RideMap extends AppCompatActivity
{
    public EditText nameText1;
    static TextView displayTime;
    static TextView displayDate;
    private EditText pickUpEditeText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride__map);
        pickUpEditeText = (EditText) findViewById(R.id.pick_up_location);
        nameText1=(EditText)findViewById(R.id.nameText1);
        displayTime = (TextView)findViewById(R.id.selected_time);
        displayDate=(TextView)findViewById(R.id.selected_date);
    }

    public void nextClick(View view)
    {
        if (pickUpEditeText.getText().length()>0 && nameText1.getText().toString().length() >0 && !displayTime.getText().equals("Pickup Time")  && !displayDate.getText().equals("Pickup Date"))
        {
            Intent intent = new Intent(this, RideType.class);
            intent.putExtra("pickUp_location", pickUpEditeText.getText().toString());
            intent.putExtra("destination_location", pickUpEditeText.getText().toString());
            intent.putExtra("pickup_time", displayTime.getText());
            intent.putExtra("pickUp_date", displayDate.getText().toString());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "All The field are required", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectTime(View v)
    {
        TimePicker mTimePicker = new TimePicker();
        mTimePicker.show(getFragmentManager(), "Select time");
    }

    public void selectDate(View view)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener
    {
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

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
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
}
