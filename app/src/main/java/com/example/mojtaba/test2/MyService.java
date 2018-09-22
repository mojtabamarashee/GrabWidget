package com.example.mojtaba.test2;

import android.app.ActivityManager;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.ViewDebug;
import android.widget.Toast;

public class MyService extends Service {
	int distroy = 0;
	Handler handler = new Handler();
	Runnable r;
	int running = 1;
   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
      return null;
   }
	
   @Override
   public int onStartCommand(Intent intent, int flags, int startId) {
      // Let it continue running until it is stopped.
      //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

	  final Context test = this;
	  //interval = intent.getIntExtra("interval", 0);
	  //WriteToFile.Write("interval in service" + Integer.toString(interval));
	   WriteToFile.Write("start of service");

	  handler.removeCallbacksAndMessages(null);
	  r = new Runnable() {
		  @Override
		  public void run() {
			  int interval = 1;
			  try {

				  SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
				  interval = prefs.getInt("interval", 1);
				  //Toast.makeText(NewAppWidget.getAppContext(), Integer.toString(interval), Toast.LENGTH_SHORT).show();


				  Intent intent = new Intent(test, NewAppWidget.class);
				  intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				  

				  int[] ids = AppWidgetManager.getInstance(getApplication())
					  .getAppWidgetIds(new ComponentName(getApplication(), NewAppWidget.class));
				  intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
				  sendBroadcast(intent);
				  WriteToFile.Write("service run");
				  //Toast.makeText(NewAppWidget.getAppContext(), "service run", Toast.LENGTH_SHORT).show();


			  }

			  catch (Exception e) {
				  String title = "service error : " + e.getMessage();
				  //Toast.makeText(NewAppWidget.getAppContext(), title, Toast.LENGTH_SHORT).show();
				  WriteToFile.Write(title);

			  }

					//if(running == 1)
			  handler.postDelayed(this, interval * 1000);

		  }
	  };

	  handler.post(r);
      return START_STICKY;

   }

	@Override
	public void onDestroy() {

		//Log.i(TAG, "onCreate() , service stopped...");
		//super.onDestroy();
		//stopService(new Intent(getBaseContext(), MyService.class));
		//stopSelf();
		//Toast.makeText(this, "on destroy", Toast.LENGTH_SHORT);

		//distroy = 1;
		handler.removeCallbacks(r);
		running = 0;


		WriteToFile.Write("service is stoped");

	}

}
