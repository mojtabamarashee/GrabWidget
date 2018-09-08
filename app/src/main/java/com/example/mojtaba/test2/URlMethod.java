import java.io.DataInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLMethod {
  public static void main(String[] argv) throws Exception {

    URL url = new URL("http://www.java.com/");
    URLConnection urlConnection = url.openConnection();
    DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
    String html = "", tmp = "";
    while ((tmp = dis.readUTF()) != null) {
      html += " " + tmp;
    }
    dis.close();

    html = html.replaceAll("\\s+", " ");
    Pattern p = Pattern.compile("<title>(.*?)</title>");
    Matcher m = p.matcher(html);
    while (m.find() == true) {
      System.out.println(m.group(1));
    }
  }
}
