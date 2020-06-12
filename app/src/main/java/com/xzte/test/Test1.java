package com.xzte.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Test1 {

    public static void main(String[] arg){
//        mm();
//        test3();

//        String webUrl1 = "http://www.bjtime.cn";//bjTime
//        String webUrl2 = "http://www.baidu.com";//百度
//        String webUrl3 = "http://www.taobao.com";//淘宝
//        String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
//        String webUrl5 = "http://www.360.cn";//360
////        String webUrl6 = "http://www.beijing-time.org";//beijing-time
////        System.out.println(getWebsiteDatetime(webUrl1) + " [bjtime]");
//        System.out.println(getWebsiteDatetime(webUrl2) + " [百度]");
//        System.out.println(getWebsiteDatetime(webUrl3) + " [淘宝]");
//        System.out.println(getWebsiteDatetime(webUrl4) + " [中国科学院国家授时中心]");
//        System.out.println(getWebsiteDatetime(webUrl5) + " [360安全卫士]");
//        System.out.println(getWebsiteDatetime(webUrl6) + " [beijing-time]");

    }

    private static void mm(){

//        BigDecimal bigDecimal = BigDecimal.ZERO;
//        System.out.println(bigDecimal.toString());
//
//        System.out.println(new BigDecimal(132.1256, new MathContext(2, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(1.0000, new MathContext(2, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(2.0000).setScale(2,RoundingMode.HALF_UP));
//        System.out.println(new BigDecimal(2.456789).setScale(0,RoundingMode.HALF_UP));
//        //相反
//        System.out.println(new BigDecimal(3.123456).negate(new MathContext(2, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(-4.123456).negate(new MathContext(2, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(123.456).plus(new MathContext(3, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(-1.456).plus(new MathContext(2, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(-1.003).round(new MathContext(2, RoundingMode.HALF_UP)));
//        System.out.println(new BigDecimal(-123.003).round(new MathContext(3, RoundingMode.HALF_UP)));
//        System.out.println(Math.round(2.123456));
//
//        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(1.00456));
//        BigDecimal bd = bigDecimal1.setScale(3, RoundingMode.HALF_UP);
//        System.out.println(bd);
//        System.out.println(bd.intValue());
//        System.out.println(bd.intValue() == bd.doubleValue());
//
//        System.out.println(bigDecimal1.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros());
//
//
//        System.out.println(new BigDecimal("0000.00001").setScale(2,RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
        System.out.println(new BigDecimal("0.00").stripTrailingZeros());
        System.out.println(new BigDecimal("0.00").stripTrailingZeros().toPlainString());
        System.out.println(new BigDecimal(Double.toString(0)).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());

        System.out.println("123".indexOf("."));

        System.out.println(test("0.000"));
        System.out.println(test("00000.000"));
        System.out.println(test("00123.100"));
        System.out.println(test("00123.100"));
        System.out.println(test("123.1230"));


    }

    public static String test(String string) {
        int dian = string.indexOf(".");
        if (dian > -1) {
            String[] splitStr = string.split("\\.");
            String qian = "";
            String hou = "";
            if(splitStr.length == 0){
                return string;
            }else if (splitStr.length == 1) {
                if(dian == 0){
                    hou = splitStr[0];
                }else{
                    qian = splitStr[0];
                }
            }else{
                qian = splitStr[0];
                hou = splitStr[1];
            }

            StringBuilder qianStr = new StringBuilder();
            StringBuilder houStr = new StringBuilder();
            boolean start0 = false;
            boolean end0 = false;
            for (int i = 0, size = qian.length(); i < size; i++) {
                char cc = qian.charAt(i);
                if (!start0 && cc == '0') {
                    continue;
                }
                start0 = true;
                qianStr.append(cc);
            }
            for (int size = hou.length(), i = size - 1; i >= 0; i--) {
                char cc = hou.charAt(i);
                if (!end0 && cc == '0') {
                    continue;
                }
                end0 = true;
                houStr.append(cc);
            }

            if (houStr.length() == 0) {
                if (qianStr.length() == 0) {
                    return "0";
                }else{
                    return qianStr.toString();
                }
            }else{
                if (qianStr.length() == 0) {
                    return "0."+houStr;
                }else{
                    return qianStr.toString() + "." + houStr;
                }
            }

        }else{
            return string;
        }
    }

    public static void test3(){
//        1579417200000
        long mm = 1579417200000L;
        Map<Long, String> hashMap = new HashMap<Long, String>();
        hashMap.put(mm, "张三");

        boolean iscz = hashMap.containsKey(mm);
        System.out.println(iscz);
        System.out.println(hashMap.get(mm));

    }

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     * @author SHANHY
     * @date   2015年11月27日
     */
    private static String getWebsiteDatetime(String webUrl){
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            System.out.println("ld = " + ld);
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
