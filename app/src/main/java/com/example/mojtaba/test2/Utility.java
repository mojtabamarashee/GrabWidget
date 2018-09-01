package com.example.mojtaba.test2;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    public static String getCurrentTime(String timeformat) {
        Format formatter = new SimpleDateFormat(timeformat);
        return formatter.format(new Date());
    }
}


