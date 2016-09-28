package com.mycompany.myapp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    TextView txt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt = (TextView)findViewById(R.id.txt);
        txt.append("onCreate()\n");
    }
    protected void onStart(){
        super.onStart();
        txt.append("[--onStart()--]\n");
    }
    protected void onStop(){
        super.onStop();
        txt.append("[--onStop()--]\n");
    }
    protected void onDestroy(){
        Toast.makeText(this, "onDestroy()\n", Toast.LENGTH_SHORT).show();
    }
    public void toNext(View v){
        Intent i = new Intent(this, NextActivity.class);
        startActivity(i);
    }
    public void close(View v){
        finish();
    }
}
