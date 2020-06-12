package com.t.httplib;

import java.security.MessageDigest;

public class Util {

	public static String changeAddressDetail(String p,String c,String q,String addressDetail){
		if(p.isEmpty()||c.isEmpty()||q.isEmpty()){
			return addressDetail.isEmpty() ? "" : addressDetail;
		}
		if( addressDetail.isEmpty()){
			return "";
		}
		if (addressDetail.startsWith("中国")) {
			addressDetail = addressDetail.substring(2);
		}
		if (addressDetail.startsWith(p)) {
			return addressDetail;
		}else if (addressDetail.startsWith(c)) {
			return p + addressDetail;
		}else if (addressDetail.startsWith(q)) {
			return p + c + addressDetail;
		}else {
			return p + c + q + addressDetail;
		}
	}
	public static String showAddressDetail(String addressDetail){
		try{
			if (addressDetail.startsWith("中国")) {
				return addressDetail.substring(2);
			}
			return addressDetail;
		}catch (Exception e){
			return addressDetail;
		}
	}
	public static String showAddressDetail2(String p,String addressDetail){
		try{
			if (addressDetail == null || addressDetail.equals("")) {
				return "";
			}
			if (addressDetail.startsWith("中国")) {
				addressDetail = addressDetail.substring(2);
			}
			if(addressDetail.startsWith(p)){
				addressDetail = addressDetail.substring(p.length());
			}
			return addressDetail;
		}catch (Exception e){
			return addressDetail;
		}
	}

	private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

	//这里主要是遍历8个byte，转化为16位进制的字符，即0-F
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}
	//这里是针对单个byte，256的byte通过16拆分为d1和d2
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 *
	 *
	 * @param origin
	 * @param charsetname
	 * @return
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
//		return resultString.toUpperCase();//返回大写MD5
		return resultString.toLowerCase();//返回小写MD5
	}

	public static String MD5EncodeUtf8(String origin) {
		return MD5Encode("RULONG" + origin + "RULONG", "utf-8");
	}
}
