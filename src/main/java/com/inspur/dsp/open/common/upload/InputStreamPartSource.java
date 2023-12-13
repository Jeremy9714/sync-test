package com.inspur.dsp.open.common.upload;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.methods.multipart.PartSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 以输入流作为文件上传的源。
 * 
 * <p>因为HttpClient只提供了FilePartSource和ByteArrayPartSource，所以需要自己实现以流作为源的功能。
 *
 */
public class InputStreamPartSource implements PartSource{
	private static Log log = LogFactory.getLog(InputStreamPartSource.class);
	
	private InputStream in;
	private String fileName;
	
	public InputStreamPartSource(InputStream in, String fileName) {
		this.in = in;
		this.fileName = fileName;
	}

	public InputStream createInputStream() throws IOException {
		return this.in;
	}

	public String getFileName() {
		return this.fileName;
	}

	public long getLength() {
		try {
			return this.in.available();
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Get inputstream length error.", e);
			}
			throw new RuntimeException(e);
		}
		
	}

}
