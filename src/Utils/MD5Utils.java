package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具
 */
public class MD5Utils {
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
//		byte[] secretBytes = null;
//		try {
//			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException("没有md5这个算法！");
//		}
//		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
//		// 如果生成数字未满32位，需要前面补0
//		for (int i = 0; i < 32 - md5code.length(); i++) {
//			md5code = "0" + md5code;
//		}
//		return md5code;

		try {
			// 获得MD5摘要算法的 MessageDigest对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(plainText.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int tmp = md[i];
				if (tmp < 0)
					tmp += 256;
				if (tmp < 16)
					buf.append("0");
				buf.append(Integer.toHexString(tmp));
			}
			// return buf.toString().substring(8, 24);// 16位加密
			return buf.toString();// 32位加密
		} catch (Exception e) {
			throw new RuntimeException("没有md5这个算法！");
		}

	}

}