package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.RemoteViews;
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
    String title;
    int cntr;

    public Title(Context context)
    {
        WriteToFile.Write("inTitle");
        this.context = context;
        title="loading ...";
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {


            //Document document2 = Jsoup.connect("http://www.jafr.gigfa.com/save.php").timeout(50 * 1000).get();
            Document document = Jsoup.connect("http://www.tgju.org/coin").timeout(50 * 1000).get();
            WriteToFile.Write("in Runnable after jsoup");
            Elements a = document.body().select("*");            WriteToFile.Write("in Runnable after jsoup");
            WriteToFile.Write("in Runnable after2");





            //title= a.select("body > main > div+ div  table> tbody > tr + tr >th").get(0).text();
            title = "سکه امامی:";
            title += a.select("body > main > div+ div  table> tbody > tr + tr >th + td").get(0).text();


            URL url = new URL("http://www.jafr.gigfa.com/save.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //readStream(in);
            } finally {
                urlConnection.disconnect();
            }

            title += "\n";
            WriteToFile.Write("in Runnable after3");


            Calendar c = Calendar.getInstance();
            WriteToFile.Write("in Runnable after4");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            WriteToFile.Write("in Runnable after5");

            String strDate = sdf.format(c.getTime());
            WriteToFile.Write("in Runnable after6");


            title += strDate;

            title += "\n";
            //title += Integer.toString(cntr);
            WriteToFile.Write("in Runnable after7");

            cntr = cntr + 1;
            WriteToFile.Write("in Runnable at End");

        } catch (Exception e) {
            title+=";error" + e.getMessage();
            WriteToFile.Write("in Runnable at Error");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        //Toast.makeText(context, "start of postExecute", Toast.LENGTH_SHORT).show();
        views.setTextViewText(R.id.appwidget_text, title);

        views.setTextViewText(R.id.appwidget_text, title);
        ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, views);
    }


}

