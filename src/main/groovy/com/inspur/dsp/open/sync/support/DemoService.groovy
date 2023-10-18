package com.inspur.dsp.open.sync.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * 数据目录索引的查询服务
 */
@Service
class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);


	String getXXX() {
		def query = """
           {
               "name": "datasync"
			}
		"""

		return query
	}

}
