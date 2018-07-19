package com.example.mojtaba.test2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {







    private class Title extends AsyncTask<Void, Void, Void> {
        private Context context;
        private AppWidgetManager appWidgetManager;
        private int appWidgetId;
        String title;

        public Title(Context context)
        {
            this.context = context;
            title="loading ...";
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //WriteToFile("in Runnable before jsoup");
                Document document = Jsoup.connect("http://www.tgju.org/coin").get();
                //WriteToFile("in Runnable after jsoup");
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
                //WriteToFile("in Runnable at End");

            } catch (Exception e) {
                title="error" + e.getMessage();
                //isWriteToFile("in Runnable at Error");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setTextViewText(R.id.appwidget_text, title);

            views.setTextViewText(R.id.appwidget_text, title);
            ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thiswidget, views);

        }



    }





















    int cntr = 0;
 @Override
 public void onReceive(final Context context, Intent intent) {
  PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
  //Acquire the lock
  wl.acquire();
  Toast.makeText(context, "on recive", Toast.LENGTH_LONG).show();

  //You can do the processing here update the widget/remote views.
  final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
          R.layout.new_app_widget);














     final RemoteViews rm = remoteViews;
     final Context contextt = context;
     final Handler handler = new Handler();
     Runnable r = new Runnable() {

   @Override
   public void run() {
       String title = "loading";
    try{

        new Title(contextt).execute();
        handler.postDelayed(this, 1  * 1000);
        //}
    }
    catch(Exception e)
    {
        //WriteToFile("run Exeption");
        title="error : " + e.getMessage()+ "title :"  + title;
    }
       //rm.setTextViewText(R.id.appwidget_text, Utility.getCurrentTime("hh:mm:ss a"));



       handler.postDelayed(this, 1   * 1000);
   }
  };
  handler.post(r);




  //remoteViews.setTextViewText(R.id.appwidget_text, Utility.getCurrentTime("hh:mm:ss a"));
  //ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
  //AppWidgetManager manager = AppWidgetManager.getInstance(context);
  //manager.updateAppWidget(thiswidget, remoteViews);
  ////Release the lock
  wl.release();
 }
}
