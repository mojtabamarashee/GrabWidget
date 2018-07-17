package com.example.mojtaba.test2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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

    String title;
    int cntr = 0;
 @Override
 public void onReceive(Context context, Intent intent) {
  PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
  //Acquire the lock
  wl.acquire();
  Toast.makeText(context, "on recive", Toast.LENGTH_LONG).show();

  //You can do the processing here update the widget/remote views.
  RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
          R.layout.new_app_widget);






  /*try{
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

  }
  catch (Exception e) {
   title="error : " + e.getMessage();
  }

*/





  remoteViews.setTextViewText(R.id.appwidget_text, Utility.getCurrentTime("hh:mm:ss a"));
  ComponentName thiswidget = new ComponentName(context, NewAppWidget.class);
  AppWidgetManager manager = AppWidgetManager.getInstance(context);
  manager.updateAppWidget(thiswidget, remoteViews);
  //Release the lock
  wl.release();
 }
}
