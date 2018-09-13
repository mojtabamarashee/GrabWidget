package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Title extends AsyncTask<Void, Void, Void> {
    private Context context;
    private AppWidgetManager appWidgetManager;
    private int appWidgetId;
    String title, title2, date;
    int cntr;
    int error = 0;

    public Title(Context context) {
        WriteToFile.Write("inTitle");
        this.context = context;
        title = "loading ...";
        title2 = "loading ...";

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(c.getTime());
            date = strDate;

            Document document = Jsoup.connect("http://www.tgju.org/coin").timeout(20 * 1000).get();
            WriteToFile.Write("in Runnable after jsoup");
            Elements a = document.body().select("*");
            WriteToFile.Write("in Runnable after2");


            ////title= a.select("body > main > div+ div  table> tbody > tr + tr >th").get(0).text();


            title = "سکه امامی:";
            title += a.select("body > main > div+ div  table> tbody > tr + tr >th + td").get(0).text();

            title2 = "نیم سکه:";
            title2 += a.select("body > main > div+ div  table> tbody > tr + tr + tr>th + td").get(0).text();

            //title += URIMethod.GetData();
            URIMethod.GetData();


            title += "\n";
            title2 += "\n";


            WriteToFile.Write("in Runnable after6");

            title += "\n";
            title2 += "\n";

            //title += Integer.toString(cntr);
            WriteToFile.Write("in Runnable after7");

            cntr = cntr + 1;
            WriteToFile.Write("in Runnable at End");
            error = 0;

        } catch (Exception e) {
            // title="error !";
            //title2="error !";
            WriteToFile.Write("in Runnable at Error");
            error = 1;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        if (error == 0) {
            //Toast.makeText(context, "start of postExecute", Toast.LENGTH_SHORT).show();
            views.setTextViewText(R.id.emami_text, title);
            views.setTextViewText(R.id.nim_text, title2);
            views.setTextViewText(R.id.date_text, date);

            views.setTextColor(R.id.date_text, Color.WHITE);
            //views.setInt(R.id.date_text, "setBackgroundColor", android.graphics.Color.RED);

        }
        else {

            //views.setInt(R.id.date_text, "setBackgroundColor", android.graphics.Color.RED);
            views.setTextColor(R.id.date_text, android.graphics.Color.RED);
        }


        //views.setTextViewText(R.id.emami_text, title);
        ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        WriteToFile.Write("update widget");
        manager.updateAppWidget(thiswidget, views);

    }
}

