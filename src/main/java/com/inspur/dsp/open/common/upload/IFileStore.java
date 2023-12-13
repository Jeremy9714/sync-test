package com.inspur.dsp.open.common.upload;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 文件存储
 * 
 * @author zhfeng
 *
 */
public interface IFileStore {
	
	
	/** 文件存储类型，RC或OSS*/
	public static final String FILESTORE_TYPE = "filestore_type";
		
	/**
	 * 以文件对象作为源，增加文件
	 * 
	 * @param fileName 文件名称
	 * @param file 新增文件对象
	 * @param otherParams 其他参数
	 * @return 文件的唯一标识，如果失败，则返回null
	 */
	String putFile(String fileName, File file, Map<String, String> otherParams);
	
	
	
	/**
	 * 以输入流作为源，增加文件
	 * 
	 * @param fileName 文件名称
	 * @param in 输入流
	 * @param otherParams 其他参数
	 * @return 文件的唯一标识，如果失败，则返回null
	 */
	public String putFile(String fileName, InputStream in, Map<String, String> otherParams);


	/**
	 *
	 * @param fileId 文件的唯一标识
	 * @param reader 读取文件
	 * @param headerReader 读取下载文件的响应头
	 */
	public void getFile(String fileId, FileReader reader, ResponseHeaderReader headerReader);
	
	/**
    *
    * @param urlStr 文件的url
    * @param fileStorePath 文件下载后的存储路径
    * @param fileName 文件下载后的文件名
    * @return 如果成功，则返回true，如果失败，则返回false
    */
	public boolean downloadFile(String urlStr, String fileStorePath, String fileName);
	
	/**
    *
    * @param urlStr 文件的url
    * @param fileStorePath 文件下载后的存储路径
    * @param fileName 文件下载后的文件名
    * @return 如果成功，则返回下载的文件，如果失败，则返回null
    */
	public File downloadFileByStream(String urlStr, String fileStorePath, String fileName);
	
	/**
    *
    * @param urlStr 文件的url
    * @return 如果成功，返回文件字节流；如果失败，返回null
    */
    public byte[] downloadFile(String urlStr);

}
