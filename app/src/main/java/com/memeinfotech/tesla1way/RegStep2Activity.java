package com.memeinfotech.tesla1way;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class RegStep2Activity extends AppCompatActivity
{
    public EditText zipcode;
    public Spinner spinner, spinner1, spinner2;
    public String name, phone, email, password, address, city, state, country, zip, user;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_step2);
        getSupportActionBar().hide();

        zipcode = (EditText) findViewById(R.id.zipcode);

        System.out.println("Name trace :------------" + getIntent().getExtras().getString("name"));
        name = getIntent().getExtras().getString("name");
        phone = getIntent().getExtras().getString("phone");
        email = getIntent().getExtras().getString("email");
        password = getIntent().getExtras().getString("password");
        address = getIntent().getExtras().getString("address");
        user = getIntent().getExtras().getString("user");

        spinner = (Spinner) findViewById(R.id.city_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1 = (Spinner) findViewById(R.id.state_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.state_spinner, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.country_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.country_spinner, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }

    public void onSubmit(View view)
    {
        city = spinner.getSelectedItem().toString();
        country = spinner2.getSelectedItem().toString();
        state = spinner1.getSelectedItem().toString();
        zip = zipcode.getText().toString();

        if(city.length() <= 0 || country.length() <= 0 || state.length() <= 0 || zip.length() <= 0)
            return;

        RequestParams rp = new RequestParams();
        rp.add("password", password);
        rp.add("role", user);
        rp.add("phoneNumber",phone);
        rp.add("email",email);
        rp.add("name", name);
        rp.add("city", city);
        rp.add("state", state);
        rp.add("zipcode", zip);
        rp.add("country", country);
        System.out.println(rp);

        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        HttpUtils.postByUrl("http://ec2-52-27-118-19.us-west-2.compute.amazonaws.com:5555/user",rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                progress.dismiss();
                //If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                try
                {
                    JSONObject serverResp = new JSONObject(response.toString());
                    System.out.println("success result: " + serverResp);

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                    SharedPreferences.Editor editor = pref.edit();

                    if((serverResp.getString("error").equals("false")) && (serverResp.getJSONObject("result").getString("role").equals("user"))) {
                        Intent i = new Intent(RegStep2Activity.this, MapsActivity.class);
                        startActivity(i);

                        String roleName = serverResp.getJSONObject("result").getString("role");
                        editor.putString("role", roleName);
                        editor.putBoolean("loginStatus", true);
                        editor.commit();
                    }
                    else if((serverResp.getString("error").equals("false")) && (serverResp.getJSONObject("result").getString("role").equals("driver")))
                    {
                        Intent in = new Intent(RegStep2Activity.this,DriverDashboardActivity.class);
                        startActivity(in);

                        String roleName = serverResp.getJSONObject("result").getString("role");
                        editor.putString("role", roleName);
                        editor.putBoolean("loginStatus", true);
                        editor.commit();
                    }
                }
                catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("dskf","fdfh");
                progress.dismiss();
            }
        });
    }
}