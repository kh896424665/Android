package com.example.kh.activitylifecycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TAG","(1)onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onStart(){
        Log.i("TAG","(2)onStart()");
        super.onStart();
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("TAG","(3)onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
    protected void onResume(){
        Log.i("TAG","(3)onResume()");
        super.onResume();
    }
    protected void onSaveInstanceState(Bundle outState){
        Log.i("TAG","(2)onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }
    protected void onRestart(){
        Log.i("TAG","(2)onRestart()");
        super.onRestart();
    }
    protected void onPause(){
        Log.i("TAG","(2)onPause()");
        super.onPause();
    }
    protected void onStop(){
        Log.i("TAG","(2)onStop()");
        super.onStop();
    }
    protected void onDestroy(){
        Log.i("TAG","(2)onDestroy()");
        super.onDestroy();
    }
}
