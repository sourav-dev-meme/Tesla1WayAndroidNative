//
//package com.memeinfotech.tesla1way;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpException;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
///*import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;*/
//
//public class JSONParser {
//
//    // constructor
//    public JSONParser() {
//    }
//
//    public String getJSONFromUrl(String url, List<NameValuePair> params) throws URISyntaxException, HttpException, JSONException
//    {
//        /*System.out.println("LoginBackground | doInBackground ::: url::" + url);
//        System.out.println("LoginBackground | doInBackground ::: username::" + params.get(0).getValue());
//        System.out.println("LoginBackground | doInBackground ::: passWord::" + params.get(1).getValue());*/
//
//        InputStream is = null;
//        JSONObject jObj = null;
//        String json = null;
//        try {
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Authorization", "Basic bWVtZWFwaWFjY2VzczpnYWVzcHJpbmdhbmRyb2lkMjAxNg==");
//            httpPost.setEntity(new UrlEncodedFormEntity(params));
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null)
//            {
//                sb.append(line);
//                System.out.println("Line==="+line);
//            }
//            //System.out.println("Response from server===="+sb.toString());
//            is.close();
//            json = sb.toString();
//            //jObj = new JSONObject(json);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return json;
//    }
//
//
//    //List<NameValuePair> params
//    public String getJSONFromUrl2(String url, HashMap<String, String> headerParam) throws URISyntaxException, HttpException, JSONException {
//        //System.out.println("Enter into JSONParser getJSONFromURL2");
//        InputStream is = null;
//        JSONObject jObj = null;
//        String json = null;
//        try {
//            //System.out.println("enter into try block in getJSONFromURL2 method");
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpGet httpGet = new HttpGet(url);
//            Iterator myIterator = headerParam.keySet().iterator();
//            while (myIterator.hasNext()) {
//                String key = (String) myIterator.next();
//                String value = (String) headerParam.get(key);
//                //System.out.println("key==="+key+"===value==="+value);
//                httpGet.addHeader(key, value);
//            }
//            //System.out.println("httpGet==="+httpGet);
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            is = httpEntity.getContent();
//            //System.out.println("is====" + is);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    is, "iso-8859-1"), 8);
//            //System.out.println("reader===" + reader);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//                //System.out.println("sb===" + sb);
//            }
//            //System.out.println("Response from server===="+sb.toString());
//            is.close();
//            json = sb.toString();
//
//
//        } catch (Exception e) {
//
//        }
//        return json;
//
//    }
//}