package ru.mr123150.znachit;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static final String APP_PREFERENCES = "settings";
	public static final String APP_PREFERENCES_COUNT = "Count";
	public static final String APP_PREFERENCES_WORD = "Word";
	public static final String APP_PREFERENCES_TIMER = "Timer";
	
	SharedPreferences settings;

	int count=0;
	int timerCount=0;
	
	double minAverage=0;
	double pairAverage=0;
	
	String word="";
	
	Button countBtn;
	Button resetBtn;
	
	TextView countText;
	TextView timerText;
	TextView averageText;
	
	Timer timer = new Timer();
	
	class timerTick extends TimerTask{
		
		@Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                	++timerCount;
                	minAverage=(double)count/(double)timerCount*60;
                	pairAverage=minAverage*95;
                	
        			timerText.setText("За " + Integer.toString(timerCount/60) + " мин. " + Integer.toString(timerCount%60) + " сек.");
        			averageText.setText("(" + String.format("%.2f", minAverage) + " раз/мин., " + String.format("%.2f", pairAverage) + " раз за пару)");

    				Editor editor = settings.edit();
    				editor.putInt(APP_PREFERENCES_TIMER, timerCount);
    				editor.apply();
                }
            });
        }
			
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		countBtn = (Button)findViewById(R.id.add);
		resetBtn = (Button)findViewById(R.id.reset);
		countText = (TextView)findViewById(R.id.count);
		timerText = (TextView)findViewById(R.id.time);
		averageText = (TextView)findViewById(R.id.avg);
		
	    count=settings.getInt(APP_PREFERENCES_COUNT, 0);
	    word=settings.getString(APP_PREFERENCES_WORD, "Значит");
	    timerCount=settings.getInt(APP_PREFERENCES_TIMER, 0);
	    
		countText.setText(Integer.toString(count));
		
		timer.scheduleAtFixedRate(new timerTick(),0,1000);
		
		OnClickListener pressCount = new OnClickListener(){

			@Override
			public void onClick(View v) {
				++count;
				countText.setText(Integer.toString(count));
				
				Editor editor = settings.edit();
				editor.putInt(APP_PREFERENCES_COUNT, count);
				editor.apply();
			}
		};
		
		OnClickListener pressReset = new OnClickListener(){

			@Override
			public void onClick(View v) {
				count=0;
				timerCount=0;
				countText.setText(Integer.toString(count));
				
				Editor editor = settings.edit();
				editor.putInt(APP_PREFERENCES_COUNT, count);
				editor.apply();
			}
		};
		
		countBtn.setOnClickListener(pressCount);
		if(resetBtn != null){
			resetBtn.setOnClickListener(pressReset);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		
	}
	
	@Override
	public void onStop(){
		super.onStop();
		
		timer.cancel();
		
		Editor editor = settings.edit();
		editor.putInt(APP_PREFERENCES_COUNT, 0);
		editor.putInt(APP_PREFERENCES_TIMER, 0);
		editor.apply();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Intent intent;
	    switch (item.getItemId()) {
	    case R.id.action_reset:
	    	count=0;
	    	timerCount=0;
			countText.setText(Integer.toString(count));
			
			Editor editor = settings.edit();
			editor.putInt(APP_PREFERENCES_COUNT, count);
			editor.apply();
	        return true;
	    case R.id.action_settings:
	    	intent = new Intent(MainActivity.this, SettingsActivity.class);
	        startActivity(intent);
	        return true;
	    case R.id.action_about:
	    	intent = new Intent(MainActivity.this, AboutActivity.class);
	        startActivity(intent);
	        return true;
	    case R.id.action_user:
	    	intent = new Intent(MainActivity.this, ProfileActivity.class);
	        startActivity(intent);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

}
