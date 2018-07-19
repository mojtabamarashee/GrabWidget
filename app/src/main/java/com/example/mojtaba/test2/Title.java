package com.example.mojtaba.test2;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Title extends AsyncTask<String, Void, Void> {
        private Context context;
        private AppWidgetManager appWidgetManager;
        private int appWidgetId;
        String title;
        int cntr = 0;

        public Title()
        {
            title="loading ...";
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(String ...params) {
            try {
                WriteToFile.Write("in Runnable before jsoup");
                Document document = Jsoup.connect("http://www.tgju.org/coin").get();
                WriteToFile.Write("in Runnable after jsoup");
                Elements a = document.body().select("*");

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
                WriteToFile.Write("in Runnable at End");

            } catch (Exception e) {
                title="error" + e.getMessage();
                WriteToFile.Write("in Runnable at Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Context context = NewAppWidget.getAppContext();
            WriteToFile.Write("at start of postExecute");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setTextViewText(R.id.appwidget_text, title);

            views.setTextViewText(R.id.appwidget_text, title);
            ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thiswidget, views);
            WriteToFile.Write("at end of postExecute");

        }



}





