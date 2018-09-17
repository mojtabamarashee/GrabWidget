package com.example.mojtaba.test2;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
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
	  WriteToFile.Write("in URIMEthod");

	  URL url = new URL("http://www.tgju.org/coin");
	  URLConnection urlConnection = url.openConnection();
	  urlConnection.setConnectTimeout(15 * 1000);

	  DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
	  WriteToFile.Write("data recv");


	  /*String html="", tmp = "";
	  try {
		  while ((tmp = dis.readUTF()) != null) {
			  html+=(tmp);
		  }
	  }
	  catch(Exception e){


	  }*/

	  String html = getStringFromInputStream(dis);

	  WriteToFile.Write("before close");

	 /* for(int i = 0; i < 200; i++) {
		  WriteToFile.Write("html3 = "+ html);

	  }*/

		  dis.close();

	  html = html.replaceAll("\\s+", "");

	  html = html.replaceAll(",", "");
	  WriteToFile.Write("html3 = "+ html);


	  //context = NewAppWidget().getAppContext();
	  Pattern p = Pattern.compile("<title>(.*?)</title>");
	  Pattern p1 = Pattern.compile("<th>سکهامامی</th><td>(4\\d{7})</td>");
	//  String my = getStringFromInputStream(dis);

	  Matcher m = p1.matcher(html);
	  WriteToFile.Write("before pattern match");

	  while (m.find() == true) {

		  WriteToFile.Write("find pattern");

		  //WriteToFile.Write("find pattern1"+ m.group(1));

		 // WriteToFile.Write("find pattern2"+ m.group(2));
		 // WriteToFile.Write("html5 = "+ html);

		  return (m.group(1));
	  }

	 // WriteToFile.Write("html2 = "+ html);

	  WriteToFile.Write("before exit");



      return ("error");
  }

	public static String getStringFromInputStream(InputStream stream) throws IOException
	{
		int n = 0;
		char[] buffer = new char[1024 * 4];
		InputStreamReader reader = new InputStreamReader(stream, "UTF8");
		StringWriter writer = new StringWriter();
		while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
		return writer.toString();
	}
}
