package com.exsample.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainStore {

	private DBOpenHelper		m_helper;

	private SQLiteDatabase	m_db;
	private static final String TBL_NAME = "Test";	

	
	public MainStore( Context context ) {
		m_helper = DBOpenHelper.getInstance( context );
		if( m_helper != null )
			m_db = m_helper.getWritableDatabase();
		else
			m_db = null;
	}
	

	public void close() {
		m_db.close();
	}

	
	public void add( String caption ) {
		ContentValues val = new ContentValues();

		val.put( "caption", caption );
		m_db.insert( TBL_NAME, null, val );

	}

	public void update( int id, String caption ) {
		ContentValues val = new ContentValues();

		val.put( "caption", caption );
		m_db.update( TBL_NAME, val, "_id=?", new String[] { Integer.toString( id ) });		
	}
	
	public String[] loadAll()
	{
		int						i;
		Cursor					c;
		String[]		entries;

        
		if( m_db == null )
        	return null;

		
        c = m_db.query( TBL_NAME,
						new String[] { "_id", "caption" },
						null, null, null, null, null );
		
		int numRows = c.getCount();

        c.moveToFirst();

        entries = new String[numRows];
		for( i = 0; i < numRows; i++ )
		{
			entries[i] = c.getString(1);
			c.moveToNext();
 		}
		c.close();

		return entries;
	}
}
