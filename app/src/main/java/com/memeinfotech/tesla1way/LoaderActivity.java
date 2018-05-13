//package com.memeinfotech.tesla1way;
//
//import android.content.AsyncTaskLoader;
//import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class LoaderActivity extends AsyncTaskLoader<List<String>> {
//
//    private List<String> cacheData;
//
//    public LoaderActivity(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected void onStartLoading() {
//        if(cacheData == null){
//            forceLoad();
//
//        }
//        else {
//            super.deliverResult(cacheData);
//        }
//    }
//
//    @Override
//    public List<String> loadInBackground() {
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        List<String> data = Arrays.asList(getContext().getResources().getStringArray(""));
//        return data;
//    }
//
//    @Override
//    public void deliverResult(List<String> data) {
//        super.deliverResult(data);
//    }
//}
