package com.inspur.dsp.open.sync.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * AES 对称加密算法
 */
public class AESUtil {

	private static Log log = LogFactory.getLog(AESUtil.class);
	private static String aes_key_path = "aes.key";

	// 加载密钥文件
	private static SecretKey privateKey = null;
	static {
		try {
			InputStream inputStream = Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(aes_key_path);
			byte[] bytes = toByteArray(inputStream);
            privateKey = new SecretKeySpec(bytes, "aes");
		} catch (FileNotFoundException e) {
			log.error("未找到密钥文件：" + aes_key_path, e);
		} catch (IOException e) {
			log.error("读取密钥文件出错＿" + aes_key_path, e);
		}
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param key
	 * @return String
	 */
	public static String encode(String content, SecretKey key) {
		try {
			Cipher cipher = AESUtil.getCipherInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] b = cipher.doFinal(content.getBytes("utf-8"));
			return Base64.encodeBase64String(b);
		} catch (NoSuchPaddingException e) {
			log.error("", e);
		} catch (InvalidKeyException e) {
			log.error("", e);
		} catch (IllegalBlockSizeException e) {
			log.error("", e);
		} catch (BadPaddingException e) {
			log.error("", e);
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		}
		return null;
	}

	// 使用密钥文件中的密钥进行加密解密
	public static String encode(String content) {
		return encode(content, privateKey);
	}
	public static String decode(String miwen) {
		return decode(miwen, privateKey);
	}

	public static void main(String[] args) {
		System.out.println(decode(decode("otx8gJE9Mem5aYnY/nZpp9QEICNSzswOjlnQCw3kBi8=")));
	}

	/**
	 * AES解密
	 * 
	 * @param miwen
	 * @param key
	 * @return String
	 */
	public static String decode(String miwen, SecretKey key) {
		try {
			Cipher cipher = AESUtil.getCipherInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.decodeBase64(miwen)), "utf-8");
		} catch (NoSuchPaddingException e) {
			log.error("解密" + miwen + "出错", e);
		} catch (InvalidKeyException e) {
			log.error("解密" + miwen + "出错", e);
		} catch (UnsupportedEncodingException e) {
			log.error("解密" + miwen + "出错", e);
		} catch (IllegalBlockSizeException e) {
			log.error("解密" + miwen + "出错", e);
		} catch (BadPaddingException e) {
			log.error("解密" + miwen + "出错", e);
		} catch (NoSuchAlgorithmException e) {
			log.error("解密" + miwen + "出错", e);
		}
		return miwen;
	}
	private static byte[] toByteArray(InputStream in) throws IOException {
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
	
	private static Cipher getCipherInstance(String name) throws NoSuchAlgorithmException, NoSuchPaddingException{
		return Cipher.getInstance(name);
	}
}
