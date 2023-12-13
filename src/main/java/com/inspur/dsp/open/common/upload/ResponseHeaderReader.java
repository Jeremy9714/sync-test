package com.inspur.dsp.open.common.upload;

import java.util.List;
import java.util.Map;

public interface ResponseHeaderReader {
	
	/**
	 * 读取下载文件的响应头
	 * @param headers 头的列表，map中的key包括name和value
	 */
	public void readHeader(List<Map<String, String>> headers);

}
