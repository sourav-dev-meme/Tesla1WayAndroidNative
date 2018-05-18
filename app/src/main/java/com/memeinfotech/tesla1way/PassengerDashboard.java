package com.memeinfotech.tesla1way;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PassengerDashboard extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_dashboard);
    }

    public void nextClick(View view)
    {
        Intent rideTypeIntent = new Intent(this, RideMap.class);
        startActivity(rideTypeIntent);
    }
}
