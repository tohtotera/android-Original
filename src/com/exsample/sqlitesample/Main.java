package com.exsample.sqlitesample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends Activity {
    private String[] data;
    private MainStore store;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.main );

        ListView list = (ListView)findViewById( R.id.ListView01 );

        store = new MainStore( this );
        store.add("Xperia");
        store.add("NexusOne");
        store.add("desire");
        data = store.loadAll();
        store.close();
                
        ArrayAdapter<String> arrayAdapter
            = new ArrayAdapter<String>(this, R.layout.rowitem, data);

        list.setAdapter(arrayAdapter);
   }
}