package com.inspur.dsp.open.sync.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SyncDataUtil {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(getCurrentTime());
    }

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static final String CURRENT_ID = "206efe3f-a6d3-4889-937a-b943fb59010a";

    public static final String CURRENT_NAME = "share-open-datasync中间程序";

    public static String getCurrentTime(){
        return DATE_FORMAT.format(new Date());
    }

}
