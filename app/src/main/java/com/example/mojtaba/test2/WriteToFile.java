package com.example.mojtaba.test2;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class WriteToFile {

	static void Write(String data) {
		String path = "test2";
		String name = "test.txt";
		String time = Utility.getCurrentTime("hh:mm:ss a");
		String data2 = data + "\n";
		data2 += time + "\n";
		data2 += "-------------------------------------------" + "\n";
		Context context;

		File f;//Utility.getCurrentTime("hh:mm:ss a");
		f = new File(Environment.getExternalStorageDirectory(), path);
		if (!f.exists()) {
			f.mkdirs();
		}

		try {
			context = NewAppWidget.getAppContext();

			File myFile = new File(f, name);
			FileOutputStream fstream = new FileOutputStream(myFile, true);
			fstream.write(data2.getBytes());
			fstream.close();
			Toast.makeText(context, "write to file", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			context = NewAppWidget.getAppContext();
			Toast.makeText(context, "writeToFile Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();

		}
	}
}
