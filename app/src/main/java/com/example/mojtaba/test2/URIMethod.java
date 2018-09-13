package com.example.mojtaba.test2;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.RemoteViews;
import android.widget.Toast;
import android.content.Context;

import com.example.mojtaba.test2.R;

public class URIMethod {
  public static String GetData(Context context) throws Exception{

//	  Toast.makeText(context,"in uri", Toast.LENGTH_SHORT).show();

	  URL url = new URL("http://www.tgju.org/coin");
	  URLConnection urlConnection = url.openConnection();
	  DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
	  String html = "", tmp = "";
	  try {
		  while ((tmp = dis.readUTF()) != null) {
			  html += " " + tmp;
		  }
	  }
	  catch(Exception e){


	  }

	  dis.close();

	  html = html.replaceAll("\\s+", " ");
	  //context = NewAppWidget().getAppContext();
	  Pattern p = Pattern.compile("<title>(.*?)</title>");
	  Pattern p1 = Pattern.compile("<th>سکه امامی</th>td><td>(d*.?d+)</td>");
	  Matcher m = p.matcher(html);
	  while (m.find() == true) {
		  System.out.println(m.group(1));
		  //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
		 // views.setTextViewText(R.id.emami_text, "salam");
	  }
	  return (m.group(1));
  }
}
