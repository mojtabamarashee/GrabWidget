package com.example.mojtaba.test2;

import android.app.ActivityManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);




		Intent intent = new Intent(this, NewAppWidget.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
		// since it seems the onUpdate() is only fired on that:
		int[] ids = AppWidgetManager.getInstance(getApplication())
				.getAppWidgetIds(new ComponentName(getApplication(), NewAppWidget.class));
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
		sendBroadcast(intent);

		if (isMyServiceRunning(MyService.class)) {

		} else {
			startService();
		}
	}

	public void startService() {
		startService(new Intent(getBaseContext(), MyService.class));
		//Toast.makeText(NewAppWidget.getAppContext(), "service start", Toast.LENGTH_SHORT).show();
	}

	// Method to stop the service
	public void stopService() {
		stopService(new Intent(getBaseContext(), MyService.class));
		//Toast.makeText(NewAppWidget.getAppContext(), "service stop", Toast.LENGTH_SHORT).show();

	}


	private boolean isMyServiceRunning(Class<?> serviceClass) {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}
