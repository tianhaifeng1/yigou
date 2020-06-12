package com.work.doctor.fruits.assist;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DemoUtils {

    private static final String TAG = "SDK_Sample.DemoUtils";

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static byte[] getHtmlByteArray(final String url) {
        URL htmlUrl = null;
        InputStream inStream = null;
        try {
            htmlUrl = new URL(url);
            URLConnection connection = htmlUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = inputStreamToByte(inStream);

        return data;
    }

    public static byte[] inputStreamToByte(InputStream is) {
        try {
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                bytestream.write(ch);
            }
            byte imgdata[] = bytestream.toByteArray();
            bytestream.close();
            return imgdata;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static byte[] readFromFile(String fileName, int offset, int len) {
        if (fileName == null) {
            return null;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            Log.i(TAG, "readFromFile: file not found");
            return null;
        }

        if (len == -1) {
            len = (int) file.length();
        }

        Log.d(TAG, "readFromFile : offset = " + offset + " len = " + len + " offset + len = " + (offset + len));

        if (offset < 0) {
            Log.e(TAG, "readFromFile invalid offset:" + offset);
            return null;
        }
        if (len <= 0) {
            Log.e(TAG, "readFromFile invalid len:" + len);
            return null;
        }
        if (offset + len > (int) file.length()) {
            Log.e(TAG, "readFromFile invalid file len:" + file.length());
            return null;
        }

        byte[] b = null;
        try {
            RandomAccessFile in = new RandomAccessFile(fileName, "r");
            b = new byte[len]; // 创建合适文件大小的数组
            in.seek(offset);
            in.readFully(b);
            in.close();

        } catch (Exception e) {
            Log.e(TAG, "readFromFile : errMsg = " + e.getMessage());
            e.printStackTrace();
        }
        return b;
    }

    public static boolean isStringEmpty(String data) {
        if (data == null || "".equals(data))
            return true;
        return false;
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }


    public static String changeAddressDetail(String p, String c, String q, String addressDetail) {
        try{
            if (p.isEmpty() || c.isEmpty() || q.isEmpty()) {
                return addressDetail.isEmpty() ? "" : addressDetail;
            }
            if (addressDetail.isEmpty()) {
                return "";
            }
            if (addressDetail.startsWith("中国")) {
                addressDetail = addressDetail.substring(2);
            }
            if (addressDetail.startsWith(p)) {
                return addressDetail;
            } else if (addressDetail.startsWith(c)) {
                return p + addressDetail;
            } else if (addressDetail.startsWith(q)) {
                return p + c + addressDetail;
            } else {
                return p + c + q + addressDetail;
            }
        }catch (Exception e){
            return "";
        }

    }

    public static String showAddressDetail(String addressDetail) {
        try {
            if (addressDetail.startsWith("中国")) {
                return addressDetail.substring(2);
            }
            return addressDetail;
        } catch (Exception e) {
            return addressDetail;
        }
    }

    public static String showAddressDetail2(String p, String addressDetail) {
        try {
            if (addressDetail == null || addressDetail.equals("")) {
                return "";
            }
            if (addressDetail.startsWith("中国")) {
                addressDetail = addressDetail.substring(2);
            }
            if (addressDetail.startsWith(p)) {
                addressDetail = addressDetail.substring(p.length());
            }
            return addressDetail;
        } catch (Exception e) {
            return addressDetail;
        }
    }

    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

    /**
     * 是否是批发用户
     *
     * @return
     */
    public static boolean isPfUser() {
        if (DemoConstant.userStatus == 2) {
            return true;
        }
        return false;
    }

    //是否改变状态：0游客；1会员；2批发商
    public static boolean changePfUser(int currentStatus) {
        if (DemoConstant.userStatus != currentStatus) {
            return true;
        }
        return false;
    }


}