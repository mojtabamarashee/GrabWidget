package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import java.util.Calendar;
import android.os.Handler;
import java.text.SimpleDateFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;
 






/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NewAppWidgetConfigureActivity NewAppWidgetConfigureActivity}
 */


public class NewAppWidget extends AppWidgetProvider {


	static String title;
	static Context context;
	int cntr = 0;

	//private class Title extends AsyncTask<Void, Void, Void> {
	//	private Context context;
//		private AppWidgetManager appWidgetManager;
//		private int appWidgetId;
//		public Title(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
//		{
//			this.context = context;
//			this.appWidgetManager = appWidgetManager;
//			this.appWidgetId = appWidgetId;
//			title="loading ...";
//		}
//
//		@Override
//		protected void onPreExecute() {
//		}
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			try {
//				Document document = Jsoup.connect("http://www.tgju.org/coin").timeout(5*1000).get();
//				Elements a = document.body().select("*");
//				//Elements value = a.select("body").select("main").select("div").select("table").select("tbody").select("th");
//				title= a.select("body > main > div+ div  table> tbody > tr + tr >th").get(0).text();
//				title += ":";
//				title += a.select("body > main > div+ div  table> tbody > tr + tr >th + td").get(0).text();
//				title += "\n";
//
//				Calendar c = Calendar.getInstance();
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String strDate = sdf.format(c.getTime());
//
//				title += strDate;
//
//				title += "\n";
//				title += Integer.toString(cntr);
//				cntr = cntr + 1;
//
//			} catch (Exception e) {
//				title="error : " + e.getMessage();
//			}
//			return null;
//		}
////
//		@Override
//		protected void onPostExecute(Void result) {
//			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

//			views.setTextViewText(R.id.appwidget_text, title);
//			appWidgetManager.updateAppWidget(appWidgetId, views);
//		}
//	}


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {





		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
			views.setTextViewText(R.id.appwidget_text, "test2");
			appWidgetManager.updateAppWidget(appWidgetId, views);


		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		//after 1 seconds
		am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 1000, pi);
		Toast.makeText(context, "onEnable", Toast.LENGTH_LONG).show();
    }

	@Override
	public void onEnabled(Context context) {
		//super.onEnabled(context);
		//AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		//Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		//PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		//After after 3 seconds
		//am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 1000 * 5, 1000 , pi);
		//Toast.makeText(context, "onAppWidgetOptionsChanged() called", Toast.LENGTH_SHORT).show();
		super.onEnabled(context);
		NewAppWidget.context = context;
	}

	public static Context getAppContext() {
		return NewAppWidget.context;
	}


	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		//ComponentName thisWidget = new ComponentName(context,
		//		TimeWidgetProvider.class);

		//for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {
			for (int appWidgetId : appWidgetIds) {

				//Get the remote views
				//RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				//R.layout.time_widget_layout);
				// Set the text with the current time.
				//remoteViews.setTextViewText(R.id.tvTime, Utility.getCurrentTime("hh:mm:ss a"));
				//appWidgetManager.updateAppWidget(widgetId, remoteViews);

				RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
				views.setTextViewText(R.id.appwidget_text, "test");
				appWidgetManager.updateAppWidget(appWidgetId, views);
				Toast.makeText(context, "onUpdate", Toast.LENGTH_LONG).show();


		}
	}
 

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            NewAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }


    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}













