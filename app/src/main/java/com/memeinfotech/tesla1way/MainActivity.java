package com.memeinfotech.tesla1way;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import android.content.SharedPreferences;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText editText,editText2;
    private EditText phoneEdiText;
    private EditText passEdiText;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String res=pref.getString("store","");
        boolean status=pref.getBoolean("loginStatus",false);
        String role=pref.getString("role","");
        System.out.println(res);
        System.out.println(status);
        System.out.println(role);

        if(status)
        {
            System.out.println(role);
            Intent fp;

            if(role.equals("user"))
                fp = new Intent(MainActivity.this, MapsActivity.class);
            else
                fp = new Intent(MainActivity.this, DriverDashboardActivity.class);

            startActivity(fp);
            finish();
        }
        else
        {
            System.out.println("stay in login page");
        }

        phoneEdiText =  (EditText)findViewById(R.id.editText);
        passEdiText  =   (EditText)findViewById(R.id.editText2);
    }

    public void newPassenger(View view)
    {
        startActivity(new Intent(this, RegStep1Activity.class));
        finish();
    }

    public void loginClick(View view)
    {
        RequestParams rp = new RequestParams();
        rp.add("phoneNumber", phoneEdiText.getText().toString()); rp.add("password", passEdiText.getText().toString());
        System.out.println(rp);

        if(phoneEdiText.getText().toString().length() > 0 && passEdiText.getText().toString().length()> 0) {

            progress = new ProgressDialog(this);
            progress.setMessage("please wait...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();

            HttpUtils.postByUrl("http://ec2-52-27-118-19.us-west-2.compute.amazonaws.com:5555/user/login", rp, new JsonHttpResponseHandler()
            {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    // To dismiss the dialog
                    progress.dismiss();
                    //If the response is JSONObject instead of expected JSONArray
                    Log.d("asd", "---------------- this is response : " + response);
                    try
                    {
                        JSONObject serverResp = new JSONObject(response.toString());
                        System.out.println("success result: " + serverResp);

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("store", response.toString());

                        String errorStatus = serverResp.getString("error");
                        if(errorStatus.equals("true"))
                        {
                            String errorMessage= serverResp.getString("message");
                            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String roleName = serverResp.getJSONObject("result").getString("role");
                            editor.putString("role", roleName);
                            editor.putBoolean("loginStatus", true);
                            editor.commit();
                            String res = pref.getString("store", "");
                            boolean status = pref.getBoolean("loginStatus", false);
                            String role = pref.getString("role", "");
                            System.out.println(res);
                            System.out.println(status);

                            if (status)
                            {
                                System.out.println(role);
                                Intent fp;

                                if (role.equals("user"))
                                    fp = new Intent(MainActivity.this, MapsActivity.class);
                                else
                                    fp = new Intent(MainActivity.this, DriverDashboardActivity.class);

                                startActivity(fp);
                                finish();
                            }
                        }
                    } catch (JSONException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("dskf", "fdfh");
                }
            });
        }
        else
        {
            Toast.makeText(this, "PhoneNumber and Password required", Toast.LENGTH_SHORT).show();
        }
    }

    public void passRegistration(View view) {
        navigateToRegistration("user");
    }

    public void driverRegistration(View view) {
        navigateToRegistration("driver");
    }

    private void navigateToRegistration(String roll)
    {
        Intent i = new Intent(this, RegStep1Activity.class);
        i.putExtra("user",roll);
        startActivity(i);
    }
}
