package com.example.mojtaba.test2;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;


public class Alarm
{
    public static void setAlarm(Context context, int interval)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NewAppWidget.class);
        intent.setAction("WIDGET_UPDATE");
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, NewAppWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        intent.putExtra("interval", interval);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        //am.cancel(pi);
        WriteToFile.Write("interval in setAram = " + Integer.toString(interval));
        am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval * 1000, pi);
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
