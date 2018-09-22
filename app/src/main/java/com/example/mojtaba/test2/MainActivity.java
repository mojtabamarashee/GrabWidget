package com.example.mojtaba.test2;

import android.app.ActivityManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	int interval;
	Intent serviceIntent;
	SharedPreferences.Editor editor;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences pref = getApplicationContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		editor = pref.edit();

		try {
			interval = pref.getInt("interval", 60);
		}
		catch(Exception e){
			editor.putInt("interval", 60);
			interval = 60;
			editor.apply();
		}




		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

		if(interval == 10)
			radioGroup.check(R.id.sec10);
		if(interval == 60)
			radioGroup.check(R.id.min1);
		if(interval == 5 * 60)
			radioGroup.check(R.id.min5);
		if(interval == 15*60)
			radioGroup.check(R.id.min15);
		if(interval ==30*60)
			radioGroup.check(R.id.min30);
		if(interval == 60*60)
			radioGroup.check(R.id.hour1);

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton rb = (RadioButton) group.findViewById(checkedId);
				if (null != rb) {
					Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();

					if(rb.getText().equals("10 sec")) {
						interval = 10;
					}
					if(rb.getText().equals("1 min")) {
                            interval = 60;
                    }
                    if(rb.getText().equals("5 min")) {
                        interval = 5 * 60;
                    }

                    if(rb.getText().equals("15 min")) {
                        interval = 15 * 60;
                    }

                    if(rb.getText().equals("30 min")) {
                        interval = 30 * 60;
                    }
                    if(rb.getText().equals("1 hour")) {
                        interval = 60 * 60;
                    }

                    WriteToFile.Write("after change" + (interval));
                    editor.putInt("interval", interval);
                    editor.apply();
					StartService();

				}

			}
		});

		/*final EditText field1 = (EditText)findViewById(R.id.interval);

		field1.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				WriteToFile.Write("after change" + (field1.getText()));
				interval = Integer.parseInt(field1.getText().toString());
				editor.putInt("interval", interval);
				editor.apply();

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start,
										  int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start,
									  int before, int count) {
			}
		});*/

		StartService();
	}

	public void StartService() {

		serviceIntent = new Intent(getBaseContext(), MyService.class);
		serviceIntent.putExtra("interval", interval);
		startService(serviceIntent);
	}

	// Method to stop the service
	public void StopService() {

		stopService(serviceIntent);
		Toast.makeText(this, "service stop", Toast.LENGTH_SHORT).show();

	}


	/*private boolean isMyServiceRunning(Class<?> serviceClass) {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}*/
}
