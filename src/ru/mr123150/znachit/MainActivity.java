package ru.mr123150.znachit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static final String APP_PREFERENCES = "settings";
	public static final String APP_PREFERENCES_COUNT = "Count";
	
	SharedPreferences settings;

	int count=0;
	Button countBtn;
	Button resetBtn;
	TextView countTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		countBtn = (Button)findViewById(R.id.add);
		resetBtn = (Button)findViewById(R.id.reset);
		countTxt = (TextView)findViewById(R.id.count);
		
		if(settings.contains(APP_PREFERENCES_COUNT)) {
		    count=settings.getInt(APP_PREFERENCES_COUNT, 0);
		    countTxt.setText(Integer.toString(count));
		}
		
		OnClickListener pressCount = new OnClickListener(){

			@Override
			public void onClick(View v) {
				++count;
				countTxt.setText(Integer.toString(count));
				
				Editor editor = settings.edit();
				editor.putInt(APP_PREFERENCES_COUNT, count);
				editor.apply();
			}
		};
		
		OnClickListener pressReset = new OnClickListener(){

			@Override
			public void onClick(View v) {
				count=0;
				countTxt.setText(Integer.toString(count));
				
				Editor editor = settings.edit();
				editor.putInt(APP_PREFERENCES_COUNT, count);
				editor.apply();
			}
		};
		
		countBtn.setOnClickListener(pressCount);
		resetBtn.setOnClickListener(pressReset);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
