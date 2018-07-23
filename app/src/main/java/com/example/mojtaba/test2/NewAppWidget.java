package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
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

public class NewAppWidget extends AppWidgetProvider {
	static String title;
	int cntr = 0;
	public static Context context;

    static void updateAppWidget(final Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
		Toast.makeText(context, "onEnable", Toast.LENGTH_LONG).show();
		final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
		final Handler handler = new Handler();

		Runnable r = new Runnable() {
			@Override
			public void run() {
				String title = "loading";
				try {

					WriteToFile.Write("onRuunable");
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
						new Title(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
					} else {

						new Title(context).execute();
					}

					handler.postDelayed(this, 60 * 1000);
				}catch (Exception e) {
					//WriteToFile.Write("run Exeption");
					title = "error : " + e.getMessage() + "title :" + title;
					Toast.makeText(context, title, Toast.LENGTH_LONG).show();
					WriteToFile.Write(title);

				}
			}
		};

		handler.post(r);
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













