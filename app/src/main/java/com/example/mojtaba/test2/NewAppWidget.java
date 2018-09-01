package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
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
	public static int pauseFlag = 0;
	public static Handler handler = new Handler();
	public void TogglePauseFlag()
	{

		if(pauseFlag == 0)
		{
			pauseFlag = 1;

		}
		else
		{
			pauseFlag = 0;
		}
	}

	private static final String ACTION_WIDGET_CLICK  = "automaticWidgetSyncButtonClick";
	private static final String ACTION_UPDATE_CLICK  = "automaticWidgetUpdateButtonClick";
    static void updateAppWidget(final Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
		final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);


		Toast.makeText(context, "start of update app widget", Toast.LENGTH_SHORT).show();

		//final Handler handler = new Handler();
		handler.removeCallbacksAndMessages(null);
		Runnable r = new Runnable() {
			@Override
			public void run() {
				String title = "loading";
				try {

					WriteToFile.Write("onRuunable");
					if(pauseFlag == 0) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
							new Title(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
						} else {

							new Title(context).execute();
						}
					}

					handler.postDelayed(this, 60 * 1000);
				}catch (Exception e) {
					//WriteToFile.Write("run Exeption");
					title = "error : " + e.getMessage() + "title :" + title;
					Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
					WriteToFile.Write(title);

				}
			}
		};

		handler.post(r);
		Toast.makeText(context, "handler posted", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		NewAppWidget.context = context;
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
		pauseFlag = 1;
	}



	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);

		if (ACTION_WIDGET_CLICK.equals(intent.getAction())) {

			//Toast.makeText(context, "onclick2", Toast.LENGTH_LONG).show();
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
			RemoteViews remoteViews;
			ComponentName watchWidget;

			watchWidget = new ComponentName(context, NewAppWidget.class);
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
			if(pauseFlag == 0) {
				//pauseFlag = 1;
				TogglePauseFlag();
				Toast.makeText(context, Integer.toString(pauseFlag), Toast.LENGTH_LONG).show();
				views.setImageViewResource(R.id.pause_12, R.drawable.icons8_play_filled_50);
			}
			else
			{
				//pauseFlag = 0;
				TogglePauseFlag();
				Toast.makeText(context, Integer.toString(pauseFlag), Toast.LENGTH_LONG).show();

				views.setImageViewResource(R.id.pause_12, R.drawable.icons8_pause_50);
			}
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		if (ACTION_UPDATE_CLICK.equals(intent.getAction())) {

			//Toast.makeText(context, "onclick2", Toast.LENGTH_LONG).show();
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
			RemoteViews remoteViews;
			ComponentName watchWidget;

			watchWidget = new ComponentName(context, NewAppWidget.class);
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

			updateAppWidget(context, appWidgetManager, appWidgetId);
			Toast.makeText(context, "refresh", Toast.LENGTH_SHORT).show();


			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
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

				Intent intent = new Intent (context, getClass());
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				intent.setAction(ACTION_WIDGET_CLICK);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
				views.setOnClickPendingIntent(R.id.pause_12, pendingIntent);


				intent = new Intent (context, getClass());
				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				intent.setAction(ACTION_UPDATE_CLICK);
				pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, intent, 0);
				views.setOnClickPendingIntent(R.id.refresh, pendingIntent);



				appWidgetManager.updateAppWidget(appWidgetId, views);

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













