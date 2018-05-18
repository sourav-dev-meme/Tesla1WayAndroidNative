package com.memeinfotech.tesla1way;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RideType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride__type);
    }

    public void nextClick(View view)
    {
        Intent intent = new Intent(this, AirportRide.class);
        startActivity(intent);
    }
}
