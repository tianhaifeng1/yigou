package com.t.databaselib;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    public static BigDecimal

    add(double v1, double v2) {// v1 + v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal sub(double v1, double v2) {//v1-v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal mul(double v1, double v2) {//v1*v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal div(double v1, double v2) {//v1/v2
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // 2 = 保留小数点后两位   ROUND_HALF_UP = 四舍五入
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
    }
    
    public static BigDecimal roundOff(BigDecimal bigDecimal,int bpi) {
        return bigDecimal.setScale(bpi, RoundingMode.HALF_UP).stripTrailingZeros();
    }
    public static BigDecimal roundOff(double number,int bpi) {
        return new BigDecimal(Double.toString(number)).setScale(bpi, RoundingMode.HALF_UP).stripTrailingZeros();
    }
    public static String roundOffString(double number,int bpi) {
        return roundOff(number,bpi).toPlainString();
    }
    public static String roundOffString(BigDecimal bigDecimal,int bpi) {
        return roundOff(bigDecimal,bpi).toPlainString();
    }

    public static String formatNumberString(String string) {
        try{
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
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

}
