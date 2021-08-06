package com.hsw.tdd.timer;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GetMicroTime {
    public void getMicroTimeFnc() {
        long nanoTime = System.nanoTime();
        long time = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        long microSeconds = (System.nanoTime() - nanoTime) / (long) 1000;
        long date = time + (microSeconds / (long) 1000);
        Timestamp t = new Timestamp(time);
        System.out.println(dateFormat.format(date) + String.format("%03d", microSeconds % (long) 1000));
    }
}