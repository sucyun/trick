package com.trick.web.common.utils.sign;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CreateMD5Utils {
	/**
	 * 创建密码的Md5
	 */
	public static String createMd5(String password) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * 创建编码
	 * 
	 * @param str    第一位字母      M10001
	 * @return
	 */
	public static String getCode(String str) {
		long l= System.currentTimeMillis();
		str=str+(l+"");
		return str;
	}

	public static void main(String[] args) throws Exception {
		String str="M";
		System.out.println(getCode(str));
//		String password = "ouPjqsjHRcDgHg20A40ZjgKugP8I";// openID
//		// 1、base64编码
//		String str = createBASE64(password);
//		System.out.println(str + "-----base64编码1");
//		// 2、des编码
//		byte[] b = encrypt(str);
//		System.out.println(b + "-------des加密2");
//		// 3、base64编码
//		String str1 = createBASE64(b.toString());
//		System.out.println(str1 + "-----base64编码3");

	}
	/**
	 * BASE64编码
	 * @param password
	 * @return
	 */
	public static String createBASE64(String password) {
		byte[] bt = password.getBytes();
		String requestValue = "";
		try {
			Base64Encoder base64 = new Base64Encoder();
			requestValue = base64.encode(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestValue;
	}


	/**
	 * Description 根据键值进行加密
	 * @throws Exception
	 */
	private static byte[] encrypt(String str) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		String str1 = "zwaddlqc";// 密钥
//		String str1 = "zcnmedia";// 密钥
		DESKeySpec dks = new DESKeySpec(str1.getBytes());

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey,sr);
		return cipher.doFinal(str.getBytes());
	}

	/**
	 * CAA加密方式
	 * 
	 * @throws Exception
	 */
	public static String base64DesBase64(String pass) throws Exception {
//		pass = "ouPjqsjHRcDgHg20A40ZjgKugP8I";// openID
		String str = createBASE64(pass);// base64编码
		byte[] b = encrypt(str);// des加密
		String str1 = createBASE64(new String(b,"utf-8"));// base64编码
		return str1;
	}
	

	/**
	 * 判断字符编码
	 * @param str
	 * @return
	 */
    public static String getEncoding(String str) {
        String encode = "GB2312";
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
               return s;      
            }      
        } catch (Exception exception) {
        }      
        encode = "ISO-8859-1";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
               return s1;      
            }      
        } catch (Exception exception1) {
        }      
        encode = "UTF-8";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
               return s2;      
            }      
        } catch (Exception exception2) {
        }      
        encode = "GBK";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
               return s3;      
            }      
        } catch (Exception exception3) {
        }      
       return "";      
    }      
	/**
	 * 将字符变成UTF8
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String setUTF8(String string) throws UnsupportedEncodingException {
		if(StringUtils.isNotBlank(string)){
			String type = CreateMD5Utils.getEncoding(string);
//			if(type.equals("GB2312")){
//				return string;
//			}
			if(type.equals("ISO-8859-1")){
				String str = new String(string.getBytes("ISO-8859-1"),"UTF-8");
				type=CreateMD5Utils.getEncoding(str);
				while( ! (type.equals("GB2312"))  ){
					 str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
					 if(CreateMD5Utils.getEncoding(str).equals("GB2312")){
						 return str;
					 }
				}
				
				return str;
			}
		}
		return string;
	}
	
	
}
