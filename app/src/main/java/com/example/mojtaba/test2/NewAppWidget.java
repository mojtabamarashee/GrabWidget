package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;

import java.io.IOException;

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
				Document document = Jsoup.connect("http://www.tgju.org/coin").get();
				Elements a = document.body().select("*");;
				//Elements value = a.select("body").select("main").select("div").select("table").select("tbody").select("th");
				title="سکه امامی:";
				title += a.select("body > main > div+ div  table> tbody > tr + tr >th + td").get(0).text();
			} catch (IOException e) {
				e.printStackTrace();
				title="error";
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
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);
			new Title(context, appWidgetManager, appWidgetId).execute();
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
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}















