package ru.mr123150.znachit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	public static final String APP_PREFERENCES = "settings";
	public static final String APP_PREFERENCES_WORD = "Word";
	
	SharedPreferences settings;
	
	EditText WordEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		WordEdit = (EditText)findViewById(R.id.word);
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		WordEdit.setText(settings.getString(APP_PREFERENCES_WORD, "Значит"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    switch (item.getItemId()) {
	    case R.id.action_save:
	    	finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

}
