package com.t.httplib.yigou.bean.resp;

import com.t.databaselib.bean.PresellInfoBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品详情的bean
 */
public class GoodsDetailInfoBean {

//            "sellTotalNum": 2,
//            "commentTotal": 1,
//            "goodsCode": "",
//            "type": 0,
//                    "catid": 1608,

    private int id;
    private String goodsName;
    //商品价格
    private BigDecimal sellPrice = BigDecimal.ZERO;
    //会员价格
    private BigDecimal sellPriceDiscount = BigDecimal.ZERO;
    private String goodsImage;
    private String goodsDetail;
    //库存
    private int stock;
    //图片的集合
    private List<Images> imageList;
    //评论数
    private int commentTotal;

    private String description;

    private String shopName;
    private int shopId;

    //分类id
    private String catid;
    //分类名称
    private String cnname;
    private int sellTotalNum;
    private int type;

//    type新加	商品类型	int	必选（0:普通商品 3:预售商品）
//    presell新加	预售信息	object
//    stock新加	销售库存	int	可选（预售商品必填）
//    sellTotalNum新加	销量	int	可选（预售商品必填）
//    sellTotalNum新加	销量	int	可选（预售商品必填）
//    presellPrice新加	预售价格	int	可选（预售商品必填）
//    buyNum新加	当前用户购买数量	int	可选（预售商品必填）
//    startTime新加	预售开始时间	string	可选（预售商品必填）
//    endTime新加	预售结束时间	string	可选（预售商品必填）
//    deliveryTime新加	送货时间	string	可选（预售商品必填）

    private PresellInfoBean presell;

    private int goodsType;

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public int getSellTotalNum() {
        return sellTotalNum;
    }

    public void setSellTotalNum(int sellTotalNum) {
        this.sellTotalNum = sellTotalNum;
    }

    public PresellInfoBean getPresell() {
        return presell;
    }

    public void setPresell(PresellInfoBean presell) {
        this.presell = presell;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    private int specialSale;

    private int isTemai;

    public int getSpecialSale() {
        return specialSale;
    }

    public void setSpecialSale(int specialSale) {
        this.specialSale = specialSale;
    }

    public int getIsTemai() {
        return isTemai;
    }

    public void setIsTemai(int isTemai) {
        this.isTemai = isTemai;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getSellPriceDiscount() {
        return sellPriceDiscount;
    }

    public void setSellPriceDiscount(BigDecimal sellPriceDiscount) {
        this.sellPriceDiscount = sellPriceDiscount;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Images> getImageList() {
        return imageList;
    }

    public void setImageList(List<Images> imageList) {
        this.imageList = imageList;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }


    public static class Images{

        /**
         * id : 4908
         * goodsImageName :
         * goodsId : 14
         * sort : 0
         * updateTime : null
         * addUser : 281
         * inuse : 0
         * goodsImage : https://newdoctor.oss-cn-beijing.aliyuncs.com/goodsImg/1552529875.png?x-oss-process=style/zip
         * updateUser : null
         * addTime : 1552892601000
         */

        private int id;
        private String goodsImageName;
        private int goodsId;
        private int sort;
        private long updateTime;
        private int addUser;
        private int inuse;
        private String goodsImage;
        private String updateUser;
        private long addTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGoodsImageName() {
            return goodsImageName;
        }

        public void setGoodsImageName(String goodsImageName) {
            this.goodsImageName = goodsImageName;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getAddUser() {
            return addUser;
        }

        public void setAddUser(int addUser) {
            this.addUser = addUser;
        }

        public int getInuse() {
            return inuse;
        }

        public void setInuse(int inuse) {
            this.inuse = inuse;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}