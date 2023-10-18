package com.inspur.dsp.open.sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages="com.inspur")
public class DataSyncApplication {
    public static void main( String[] args ) {
       SpringApplication.run(DataSyncApplication.class, args);
    }
}
