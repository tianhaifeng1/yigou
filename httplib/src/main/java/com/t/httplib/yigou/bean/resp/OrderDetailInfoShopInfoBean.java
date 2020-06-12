package com.t.httplib.yigou.bean.resp;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetailInfoShopInfoBean implements Serializable {

    /**
     * sellPrice : 1.8
     * totalNum : 1
     * goodsId : 1085
     * systemOrderNo : egouwx201905290923564
     * totalFee : 1.8
     * attrStrValue :
     * attrStrId :
     * memberId : 78
     * goodsDesc : 伊利优酸乳原味牛奶250ML
     * goodsImage : https://rulongegou.oss-cn-beijing.aliyuncs.com/goodsImg/1557732001.png
     * goodsName : 伊利优酸乳原味牛奶250ML
     */
    private BigDecimal sellPrice = BigDecimal.ZERO;
    private int totalNum;
    private int goodsId;
    private String systemOrderNo;
    private BigDecimal totalFee = BigDecimal.ZERO;
    private String attrStrValue;
    private String attrStrId;
    private int memberId;
    private String goodsDesc;
    private String goodsImage;
    private String goodsName;

    //评价提交内容
    private String commentText;

//    private int specialSale;
//    private int isTemai;
//
//    public int getSpecialSale() {
//        return specialSale;
//    }
//
//    public void setSpecialSale(int specialSale) {
//        this.specialSale = specialSale;
//    }
//
//    public int getIsTemai() {
//        return isTemai;
//    }
//
//    public void setIsTemai(int isTemai) {
//        this.isTemai = isTemai;
//    }

    //分类id
    private String catid;
    //分类名称
    private String cnname;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getAttrStrValue() {
        return attrStrValue;
    }

    public void setAttrStrValue(String attrStrValue) {
        this.attrStrValue = attrStrValue;
    }

    public String getAttrStrId() {
        return attrStrId;
    }

    public void setAttrStrId(String attrStrId) {
        this.attrStrId = attrStrId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

}
