package com.inspur.dsp.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.inspur.dsp.open.sync.DataSyncApplication;
import com.inspur.dsp.open.sync.down.resource.dao.ResourceTableDao;
import com.inspur.dsp.open.sync.down.resource.bean.ResourceTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSyncApplication.class)
public class ResourceDaoTest {
    @Autowired
    private ResourceTableDao resourceTableDao;

    @Test
    public void test(){
        EntityWrapper<ResourceTable> wrapper = new EntityWrapper<>();
//        resourceTableDao.updateById(wrapper);
        Integer count = resourceTableDao.selectCount(wrapper);
        System.out.println("--------------------" + count);
//        resourceTableDao.getLatestOperationDate();
    }
}
