package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.WebView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.mojtaba.test2.NewAppWidget;
import com.example.mojtaba.test2.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Title2 extends AsyncTask<Void, Void, Void> {
    private Context context;
    private AppWidgetManager appWidgetManager;
    private int appWidgetId;
    int cntr;
    String title;
    WebView browser;

    public Title2(Context context)
    {

        this.context = context;
        this.browser = new WebView(context);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {


            //Document document2 = Jsoup.connect("http://www.jafr.gigfa.com/save.php").timeout(50 * 1000).get();


            //title = a.select("body > main > div+ div  table> tbody > tr + tr >th + td").get(0).text();

        } catch (Exception e) {
           // Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setVisibility(View.INVISIBLE);
        browser.setLayerType(View.LAYER_TYPE_NONE,null);
        browser.loadUrl("http://www.jafr.gigfa.com/save.php");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, views);
    }



}
