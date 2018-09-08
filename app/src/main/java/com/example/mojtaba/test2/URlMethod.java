import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.Toast;
import android.content.Context;

public class URLMethod {
  public static String GetData(String[] url) {

	  Context context;
	  URL url = new URL("http://www.tgju.org/coin");
	  URLConnection urlConnection = url.openConnection();
	  DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
	  String html = "", tmp = "";
	  while ((tmp = dis.readUTF()) != null) {
		  html += " " + tmp;
	  }
	  dis.close();

	  html = html.replaceAll("\\s+", " ");
	  context = NewAppWidget.getAppContext();
	  Pattern p = Pattern.compile("<title>(.*?)</title>");
	  Pattern p1 = Pattern.compile("<th>??? ?????</th>td><td>(\d*\.?\d+)</td>");
	  Matcher m = p.matcher(html);
	  while (m.find() == true) {
		  System.out.println(m.group(1));
		  Toast.makeText(context, m.group(1), Toast.LENGTH_SHORT).show();
	  }
	  return (m.group(1));
  }
}
