package com.work.doctor.fruits.activity;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTimerAssist {

    private Map<Long, List<Integer>> tagMap;
    private List<TimeTag> timerList;

    private boolean status;

    public ActivityTimerAssist() {
        this(false);
    }

    public ActivityTimerAssist(boolean status) {
        this.status = status;
        tagMap = new HashMap<>();
        timerList = new ArrayList<>();
    }

    public void setTime(long time, int itemIndex,long currentTime) {
//        Logger.t("setTime ----- tagMap = " + tagMap.size());
        if (time <= 0) {
            return;
        }
        boolean isCz = tagMap.containsKey(time);
//        Logger.t("setTime ----- isCz = " + isCz + "-- time = " + time);
        if (isCz) {
            //存在
            List<Integer> integerList = tagMap.get(time);
            if (integerList == null) {
                integerList = new ArrayList<>();
                integerList.add(itemIndex);
                tagMap.put(time, integerList);
            } else {
                integerList.add(itemIndex);
            }
        } else {
            //不存在
            List<Integer> integerList = new ArrayList<>();
            integerList.add(itemIndex);
//            Logger.t("setTime ----- integerList = " + integerList.size());
            tagMap.put(time, integerList);
//            Logger.t("setTime ----- tagMap = " + tagMap.size());
            ceartTimer(time,currentTime);
        }
    }

    private void ceartTimer(long time,long currentTime) {
//        new GetNetworkTime(currentTime -> {
            long djs = time - currentTime;
            if (djs > 0) {
                Timer timer = new Timer();
                TimeTag tag = new TimeTag();
                tag.setTimer(timer);
                tag.setTime(time);
                tag.setIndex(timerList.size());
                tag.setDjsTime(djs / 1000);
                timerList.add(tag);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        long mm = tag.getDjsTime();
                        if (mm <= 0) {
                            Message message = new Message();
                            message.what = tag.getIndex();
                            handler.sendMessage(message);
                        } else {
                            tag.setDjsTime(mm - 1);
                            if (status) {
                                Message message = new Message();
                                message.what = tag.getIndex();
                                handler.sendMessage(message);
                            }
                        }
                    }
                }, 0, 1000);
            } else {

            }
//        });

    }

    public void refresh() {
        if (timerList != null && timerList.size() > 0) {
            for (TimeTag timeTag : timerList
            ) {
                Timer timer = timeTag.getTimer();
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    System.gc();
                }
            }
            timerList.clear();
        }
        tagMap.clear();
    }


    private TimerToTimeListener listener;

    public void setOnTimerToTimeListener(TimerToTimeListener listener) {
        this.listener = listener;
    }

    public void onDestory() {
        if (timerList != null && timerList.size() > 0) {
            for (TimeTag timeTag : timerList
            ) {
                Timer timer = timeTag.getTimer();
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    System.gc();
                }
            }
        }
        timerList = null;
        tagMap = null;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            Logger.t("handleMessage------------------what = " + msg.what);
            if (timerList != null && timerList.size() > 0) {
                TimeTag tag = timerList.get(msg.what);
                long time = tag.getTime();
                if (listener != null) {
                    listener.timerToTimeEvent(tagMap.get(time), tag.getDjsTime());
                }
                if (!status || tag.getDjsTime() <= 0) {
                    Timer timer = tag.getTimer();
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                        System.gc();
                    }
                    //移除多余的数据
                    tagMap.remove(time);
                }
            }
        }
    };


    public interface TimerToTimeListener {
        /**
         * @param list 影响的行数
         * @param time 倒计时时间
         */
        void timerToTimeEvent(List<Integer> list, long time);
    }


    public static class TimeTag {
        private long time;
        private Timer timer;
        private int index;

        private long djsTime;

        public long getDjsTime() {
            return djsTime;
        }

        public void setDjsTime(long djsTime) {
            this.djsTime = djsTime;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Timer getTimer() {
            return timer;
        }

        public void setTimer(Timer timer) {
            this.timer = timer;
        }
    }

}
