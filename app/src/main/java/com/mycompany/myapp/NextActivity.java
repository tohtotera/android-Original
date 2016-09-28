package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.view.*;

public class NextActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
    }
    public void close(View v){
        finish();
    }
}
