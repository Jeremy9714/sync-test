package com.inspur.dsp.open.sync.catalog;

import com.inspur.dsp.open.sync.DataSyncApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataSyncApplication.class)
public class JobHandlerTest {

    @Autowired
    private SyncDataJobHandler syncDataJobHandler;

    @Test
    public void call() {

        try {
            syncDataJobHandler.execute(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
