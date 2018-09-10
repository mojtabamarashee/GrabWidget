package com.example.mojtaba.test2;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Intent intent = new Intent(this, NewAppWidget.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
		// since it seems the onUpdate() is only fired on that:
		 int[] ids = AppWidgetManager.getInstance(getApplication())
			.getAppWidgetIds(new ComponentName(getApplication(), NewAppWidget.class));
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
		sendBroadcast(intent);

		final Context test = this;








		final Handler handler = new Handler();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					try {

						Intent intent = new Intent(test, NewAppWidget.class);
						intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
						// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
						// since it seems the onUpdate() is only fired on that:
						int[] ids = AppWidgetManager.getInstance(getApplication())
							.getAppWidgetIds(new ComponentName(getApplication(), NewAppWidget.class));
						intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
						sendBroadcast(intent);
					}

					catch (Exception e) {
						//WriteToFile.Write("run Exeption");
						String title = "configure error : " + e.getMessage();
						Toast.makeText(NewAppWidget.getAppContext(), title, Toast.LENGTH_SHORT).show();
						WriteToFile.Write(title);

					}

					handler.postDelayed(this, 10 * 1000);

            }
			};

			handler.post(r);








    }
}
