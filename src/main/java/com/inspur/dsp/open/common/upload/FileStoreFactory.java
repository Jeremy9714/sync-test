package com.inspur.dsp.open.common.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 获取文件存储，目前支持RCService和阿里云OSS两种存储。
 * 
 * @author zhfeng
 *
 */
@Component("newFileStoreFactory")
public class FileStoreFactory {
	
	private IFileStore fileStoreInstance;
	
	@Autowired
	private RCBasedFileStore rcBasedFileStore;

	/**
	 * 获取文件存储
	 * 
	 * @return 具体的文件存储对象
	 */
	public IFileStore getFileStore() {
		
		if (fileStoreInstance != null) {
			return fileStoreInstance;
		}
		
		fileStoreInstance = rcBasedFileStore;
		return fileStoreInstance;
	}

}
