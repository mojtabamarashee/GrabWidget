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
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	int interval = 10;
	Intent serviceIntent;
	SharedPreferences.Editor editor;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences pref = getApplicationContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
		editor = pref.edit();
		setContentView(R.layout.activity_main);
		editor.putInt("interval", 10);
		editor.apply();


		final EditText field1 = (EditText)findViewById(R.id.interval);

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
		});

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
