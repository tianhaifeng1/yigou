package com.work.doctor.fruits.assist;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetNetworkTime {

    private CurrenTimeListener listener;

    public GetNetworkTime(CurrenTimeListener listener) {
        this.listener = listener;
        getBaiduNetTime();
    }

    private void getBaiduNetTime(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String webUrl = "http://www.baidu.com";
                try {
                    URL url = new URL(webUrl);// 取得资源对象
                    URLConnection uc = url.openConnection();// 生成连接对象
                    uc.connect();// 发出连接
                    long timelong = uc.getDate();// 读取网站日期时间
                    Message message = new Message();
                    message.obj = timelong;
                    handler.sendMessage(message);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            long timelong = (long) msg.obj;
            if (listener != null) {
                listener.getTimeLong(timelong);
            }
        }
    };

    public interface CurrenTimeListener{
        void getTimeLong(long currentTime);
    }
}
