package com.t.httplib.yigou.bean.resp;

import java.util.List;

public class SigninInfoBean {

    private String param;
    private List<Model> model;
    private String key;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public List<Model> getModel() {
        return model;
    }

    public void setModel(List<Model> model) {
        this.model = model;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static class Model {

        private int id;
        private int isSale;
        private long signinDate;
        private String signDay;
        private int isSignin;
        private int signNumber;
        private String goodsImage;
        private int signGoodsid;
        private String goodsName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsSale() {
            return isSale;
        }

        public void setIsSale(int isSale) {
            this.isSale = isSale;
        }

        public long getSigninDate() {
            return signinDate;
        }

        public void setSigninDate(long signinDate) {
            this.signinDate = signinDate;
        }

        public String getSignDay() {
            return signDay;
        }

        public void setSignDay(String signDay) {
            this.signDay = signDay;
        }

        public int getIsSignin() {
            return isSignin;
        }

        public void setIsSignin(int isSignin) {
            this.isSignin = isSignin;
        }

        public int getSignNumber() {
            return signNumber;
        }

        public void setSignNumber(int signNumber) {
            this.signNumber = signNumber;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public int getSignGoodsid() {
            return signGoodsid;
        }

        public void setSignGoodsid(int signGoodsid) {
            this.signGoodsid = signGoodsid;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }

}
