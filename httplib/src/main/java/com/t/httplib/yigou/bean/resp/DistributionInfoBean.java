package com.t.httplib.yigou.bean.resp;

import java.util.List;

/**
 * 配送费信息
 */
public class DistributionInfoBean {

    /**
     * id : 7
     * isDefault : 0
     * distribFee : 5.0
     * distribTimeSort : 满200包邮
     * originFee : 200.0
     */

    private List<List_other> list_other;
    private List<List_now> list_now;

    public List<List_other> getList_other() {
        return list_other;
    }

    public void setList_other(List<List_other> list_other) {
        this.list_other = list_other;
    }

    public List<List_now> getList_now() {
        return list_now;
    }

    public void setList_now(List<List_now> list_now) {
        this.list_now = list_now;
    }

    public int getDisOtherSize() {
        return disOtherSize;
    }

    public void setDisOtherSize(int disOtherSize) {
        this.disOtherSize = disOtherSize;
    }

    private int disOtherSize;

    public static class List_other{
        private String startTime;
        private int id;
        private String deliveryNotes;
        private int shopId;
        private String distribTimeSort;
        private int type;
        private String endTime;
        private int distribFee;
        private int originFee;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeliveryNotes() {
            return deliveryNotes;
        }

        public void setDeliveryNotes(String deliveryNotes) {
            this.deliveryNotes = deliveryNotes;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getDistribTimeSort() {
            return distribTimeSort;
        }

        public void setDistribTimeSort(String distribTimeSort) {
            this.distribTimeSort = distribTimeSort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getDistribFee() {
            return distribFee;
        }

        public void setDistribFee(int distribFee) {
            this.distribFee = distribFee;
        }

        public int getOriginFee() {
            return originFee;
        }

        public void setOriginFee(int originFee) {
            this.originFee = originFee;
        }
    }
    public static class List_now {
        private String startTime;
        private int id;
        private String deliveryNotes;
        private int shopId;
        private String distribTimeSort;
        private int type;
        private String endTime;
        private int distribFee;
        private int originFee;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeliveryNotes() {
            return deliveryNotes;
        }

        public void setDeliveryNotes(String deliveryNotes) {
            this.deliveryNotes = deliveryNotes;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getDistribTimeSort() {
            return distribTimeSort;
        }

        public void setDistribTimeSort(String distribTimeSort) {
            this.distribTimeSort = distribTimeSort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getDistribFee() {
            return distribFee;
        }

        public void setDistribFee(int distribFee) {
            this.distribFee = distribFee;
        }

        public int getOriginFee() {
            return originFee;
        }

        public void setOriginFee(int originFee) {
            this.originFee = originFee;
        }
    }


//    private int id;
//    private int isDefault;
//    private BigDecimal distribFee = BigDecimal.ZERO;
//    private String distribTimeSort;
//    private BigDecimal originFee = BigDecimal.ZERO;

}
