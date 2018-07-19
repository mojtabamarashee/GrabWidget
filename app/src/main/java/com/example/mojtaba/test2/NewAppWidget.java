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


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
			views.setTextViewText(R.id.appwidget_text, "test2");
			appWidgetManager.updateAppWidget(appWidgetId, views);


		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		//after 1 seconds
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 1, 1000, pi);
		Toast.makeText(context, "onEnable", Toast.LENGTH_LONG).show();
    }

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		NewAppWidget.context = context;
	}

	public static Context getAppContext() {
		return NewAppWidget.context;
	}


	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

			for (int appWidgetId : appWidgetIds) {

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









