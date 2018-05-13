//package com.memeinfotech.tesla1way;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.AsyncTask;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedOutputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.Iterator;
//
//import javax.net.ssl.HttpsURLConnection;
//
//import cz.msebera.android.httpclient.Header;
//
//public class OtpStep2Activity extends AppCompatActivity {
//
//    public String name, phone, email, password, address, city, state, country, zip;
//    int getOtp;
//    EditText myOtp;
//
//    /*
//
//    http://ec2-52-27-118-19.us-west-2.compute.amazonaws.com:5555/user
//     */
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_otp_step2);
//        getSupportActionBar().hide();
//
//        myOtp = (EditText) findViewById(R.id.myOtp);
//
//        name = getIntent().getExtras().getString("name");
//        phone = getIntent().getExtras().getString("phone");
//        email = getIntent().getExtras().getString("email");
//        password = getIntent().getExtras().getString("password");
//        address = getIntent().getExtras().getString("address");
//        city = getIntent().getExtras().getString("city");
//        state = getIntent().getExtras().getString("state");
//        country = getIntent().getExtras().getString("country");
//        zip = getIntent().getExtras().getString("zip");
//    }
//
//    public void otpSubmit(View view) {
//        getOtp = Integer.parseInt(myOtp.getText().toString());
//        System.out.println("my otp+====" + getOtp);
//        RequestParams rp = new RequestParams();
//        rp.add("name",name);
//        rp.add("phoneNumber",phone);
//        rp.add("")
//        rp.add("code",getOtp);
//        System.out.println("response:-----" + rp);
//
//        System.out.println(rp);
//
//        HttpUtils.postByUrl("http://ec2-52-27-118-19.us-west-2.compute.amazonaws.com:5555/user/verifyOtp",rp, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
//            {
//                //If the response is JSONObject instead of expected JSONArray
//                Log.d("asd", "---------------- this is response : " + response);
//                try
//                {
//                    JSONObject serverResp = new JSONObject(response.toString());
//                    System.out.println("success result: " + serverResp);
////                    if(serverResp.getString("error") == "false") {
////                        Intent i = new Intent(RegStep2Activity.this, OtpStep2Activity.class);
////                        i.putExtra("name", name);
////                        i.putExtra("phone", phone);
////                        i.putExtra("email", email);
////                        i.putExtra("password", password);
////                        i.putExtra("address", address);
////                        i.putExtra("city", city);
////                        i.putExtra("state", state);
////                        i.putExtra("country", country);
////                        i.putExtra("zipcode", zip);
////                        startActivity(i);
////                    }
//
//
//                }
//                catch (JSONException e)
//                {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                Log.d("dskf","fdfh");
//            }
//        });
//    }
//}
//
//
////    class SendPostRequest extends AsyncTask<String, Void, String> {
////
////        protected void onPreExecute() {
////        }
////
////        protected String doInBackground(String... arg0)
////        {
////
////            try {
////
////                URL url = new URL("http://ec2-52-27-118-19.us-west-2.compute.amazonaws.com:5555/user/verifyOtp"); // here is your URL path
////
////                JSONObject postDataParams = new JSONObject();
////                postDataParams.put("phoneNumber", phone);
////                postDataParams.put("code", getOtp);
////                Log.e("params", postDataParams.toString());
////
////                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////                conn.setReadTimeout(15000 /* milliseconds */);
////                conn.setConnectTimeout(15000 /* milliseconds */);
////                conn.setRequestMethod("");
////                conn.setDoInput(true);
////                conn.setDoOutput(true);
////
////                OutputStream os = conn.getOutputStream();
////                BufferedWriter writer = new BufferedWriter(
////                        new OutputStreamWriter(os, "UTF-8"));
////                writer.write(getPostDataString(postDataParams));
////
////                writer.flush();
////                writer.close();
////                os.close();
////
////                int responseCode = conn.getResponseCode();
////                System.out.println("http status:- " + responseCode);
////                if (responseCode == HttpsURLConnection.HTTP_OK) {
////
////                    BufferedReader in = new BufferedReader(new
////                            InputStreamReader(
////                            conn.getInputStream()));
////
////                    StringBuffer sb = new StringBuffer("");
////                    String line = "";
////
////                    while ((line = in.readLine()) != null) {
////
////                        sb.append(line);
////                        break;
////                    }
////
////                    in.close();
////
////                    System.out.println("result:----------------" + sb.toString());
////
////                    return sb.toString();
////
////
////                } else {
////                    return new String("false : " + responseCode);
////                }
////            } catch (Exception e) {
////                return new String("Exception: " + e.getMessage());
////            }
////        }
////
////        @Override
////        protected void onPostExecute(String result) {
////        Toast.makeText(getApplicationContext(), result,
////                Toast.LENGTH_LONG).show();
////        }
////
////
////        public String getPostDataString(JSONObject params) throws Exception {
////
////            StringBuilder result = new StringBuilder();
////            boolean first = true;
////
////            Iterator<String> itr = params.keys();
////
////            while (itr.hasNext()) {
////
////                String key = itr.next();
////                Object value = params.get(key);
////
////                if (first)
////                    first = false;
////                else
////                    result.append("&");
////
////                result.append(URLEncoder.encode(key, "UTF-8"));
////                result.append("=");
////                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
////
////            }
////            return result.toString();
////        }
////    }
////}
////
