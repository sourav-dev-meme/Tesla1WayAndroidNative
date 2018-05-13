package com.memeinfotech.tesla1way;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegStep1Activity extends AppCompatActivity
{
    public EditText nameEditText, phoneEditText, emailEditText, passwordEditText, addressEditText;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_step1_activity);
        getSupportActionBar().hide();

        nameEditText = (EditText) findViewById(R.id.nameText);
        phoneEditText = (EditText) findViewById(R.id.phoneText);
        emailEditText = (EditText) findViewById(R.id.emailText);
        passwordEditText = (EditText) findViewById(R.id.passwordText);
        addressEditText = (EditText) findViewById(R.id.addressText);
        System.out.println("my new step1 " + nameEditText);

        user = getIntent().getExtras().getString("user");
        System.out.println(user);
    }

    public void onNextClick (View view)
    {
        if(nameEditText.getText().toString().length() > 0 && phoneEditText.getText().toString().length()> 0 &&
                emailEditText.getText().toString().length()> 0 && passwordEditText.getText().toString().length()> 0 &&
                addressEditText.getText().toString().length()> 0 && user.length()> 0)
        {
            Intent i = new Intent(this, RegStep2Activity.class);
            System.out.println("name trace:----------------------" + nameEditText.getText().toString());
            i.putExtra("name", nameEditText.getText().toString());
            i.putExtra("phone", phoneEditText.getText().toString());
            i.putExtra("email", emailEditText.getText().toString());
            i.putExtra("password", passwordEditText.getText().toString());
            i.putExtra("address", addressEditText.getText().toString());
            i.putExtra("user", user);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Please fill all the field", Toast.LENGTH_SHORT).show();
        }
    }
}
