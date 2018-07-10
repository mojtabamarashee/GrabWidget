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

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NewAppWidgetConfigureActivity NewAppWidgetConfigureActivity}
 */


public class NewAppWidget extends AppWidgetProvider {


	static String title;
	int cntr = 0;

	private class Title extends AsyncTask<Void, Void, Void> {
		private Context context;
		private AppWidgetManager appWidgetManager;
		private int appWidgetId;
		public Title(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
		{
			this.context = context;
			this.appWidgetManager = appWidgetManager;
			this.appWidgetId = appWidgetId;
			title="loading ...";
		}
 
		@Override
		protected void onPreExecute() {
		}
 
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Document document = Jsoup.connect("http://www.tgju.org/coin").timeout(5*1000).get();
				Elements a = document.body().select("*");
				//Elements value = a.select("body").select("main").select("div").select("table").select("tbody").select("th");
				title= a.select("body > main > div+ div  table> tbody > tr + tr >th").get(0).text();
				title += ":";
				title += a.select("body > main > div+ div  table> tbody > tr + tr >th + td").get(0).text();
				title += "\n";

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String strDate = sdf.format(c.getTime());

				title += strDate;

				title += "\n";
				title += Integer.toString(cntr);
				cntr = cntr + 1;

			} catch (Exception e) {
				title="error : " + e.getMessage();
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(Void result) {
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

			views.setTextViewText(R.id.appwidget_text, title);
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
			views.setTextViewText(R.id.appwidget_text, title);
			appWidgetManager.updateAppWidget(appWidgetId, views);


    }



    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {

		for (final int appWidgetId : appWidgetIds) {
			new Title(context, appWidgetManager, appWidgetId).execute();
		}
		final Handler handler = new Handler();
		final Runnable r = new Runnable() {
			public void run() {
				for (final int appWidgetId : appWidgetIds) {
					new Title(context, appWidgetManager, appWidgetId).execute();
					handler.postDelayed(this, 1 * 10 * 1000);
				}
			}
		};

		handler.postDelayed(r, 5);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            NewAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}















