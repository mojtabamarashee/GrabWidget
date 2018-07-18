package com.example.mojtaba.test2;

public void WriteToFile(String data) {
		String path = "test2";
		String name = "test.txt";
		String data2 = data + "\n";

		File f = new File(Environment.getExternalStorageDirectory(), path);
		if (!f.exists()) {
			f.mkdirs();
		}


		try {
			File myFile = new File(f, name);
			FileOutputStream fstream = new FileOutputStream(myFile, true);
			fstream.write(data2.getBytes());
			fstream.close();
			//	Toast.makeText(contextt, "write to file", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			//Log.e("Exception", "File write failed: " + e.toString());
			//	Toast.makeText(contextt, e.getMessage(), Toast.LENGTH_LONG).show();

		}
	}
