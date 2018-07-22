package com.example.mojtaba.test2;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

	int cntr = 0;
	@Override
	public void onReceive(final Context context, Intent intent) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		//Acquire the lock
		wl.acquire();
		Toast.makeText(context, "on recive", Toast.LENGTH_LONG).show();

		//You can do the processing here update the widget/remote views.
		final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.new_app_widget);
		final Context contextt = context;
		final Handler handler = new Handler();

		Runnable r = new Runnable() {
			@Override
			public void run() {
				String title = "loading";
				try{

					//Toast.makeText(contextt, "onRunnable", Toast.LENGTH_LONG).show();
					WriteToFile.Write("onRuunable");
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
						new Title(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					}
					else {

						new Title(context).execute();

					}

					handler.postDelayed(this, 60  * 1000);
				}

				catch(Exception e)
				{
					//WriteToFile("run Exeption");
					title="error : " + e.getMessage()+ "title :"  + title;
					Toast.makeText(contextt, title, Toast.LENGTH_LONG).show();
					WriteToFile.Write(title);

				}
			}
		};

		handler.post(r);
		wl.release();
	}
}
