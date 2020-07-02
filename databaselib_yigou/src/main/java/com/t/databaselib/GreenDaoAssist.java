package com.t.databaselib;

import com.google.gson.Gson;
import com.t.databaselib.bean.CartShopInfoBean;
import com.t.databaselib.bean.ReqCartDeleteInfo;
import com.t.databaselib.bean.ShopInfoBean;
import com.trjx.tlibs.uils.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GreenDaoAssist {


    private DaoSession daoSession;
    private DatabaseGoodsInfoDao goodsInfoDao;
    private DatabaseShopInfoDao shopInfoDao;
    private DatabaseCityInfoDao cityInfoDao;
    private DatabaseSearchGoodsInfoDao searchGoodsInfoDao;

    public GreenDaoAssist(DatabaseApplicationAssist databaseApplicationAssist) {
        daoSession = databaseApplicationAssist.getDaoSession();
        goodsInfoDao = daoSession.getDatabaseGoodsInfoDao();
        shopInfoDao = daoSession.getDatabaseShopInfoDao();
        cityInfoDao = daoSession.getDatabaseCityInfoDao();
        searchGoodsInfoDao = daoSession.getDatabaseSearchGoodsInfoDao();
    }

    //    删除数据库数据
    public void clearDatabase() {
        try {
            daoSession.deleteAll(DatabaseGoodsInfo.class);
            Logger.i("tong_database", "清除购物车数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.i("tong_database", "清除购物车数据失败：" + e.getMessage());
        }
    }


    /**
     * 查询所有商品数据
     *
     * @return
     */
    public List<DatabaseGoodsInfo> queryAllGoods() {
        try {
            List<DatabaseGoodsInfo> goodsInfoList = daoSession.loadAll(DatabaseGoodsInfo.class);
            Logger.i("tong_database", "查询所有商品:" + (goodsInfoList == null ? 0 : goodsInfoList.size()));
            return goodsInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 首页查询购物车数据（过滤没有商品的店铺）
     *
     * @param locationShopId 定位的店铺id
     * @return
     */
    public List<DatabaseShopInfo> queryShoppingCartData(String locationShopId) {
        try {
            List<DatabaseShopInfo> databaseShopInfoList = new ArrayList<>();
            List<DatabaseShopInfo> shopInfoList = shopInfoDao.queryBuilder().orderAsc(DatabaseShopInfoDao.Properties.ShopId).list();
            if (shopInfoList != null && shopInfoList.size() > 0) {
                for (int i = 0; i < shopInfoList.size(); i++) {
                    DatabaseShopInfo shopInfo = shopInfoList.get(i);
                    ArrayList<DatabaseGoodsInfo> goodsInfoList = (ArrayList<DatabaseGoodsInfo>) goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.ShopId.eq(shopInfo.getShopId())).orderDesc(DatabaseGoodsInfoDao.Properties.GoodsAddTime).list();
                    if (goodsInfoList == null || goodsInfoList.size() == 0) {
                        //没有数据的店铺不添加
                        continue;
                    }
                    shopInfo.setGoodsInfoList(goodsInfoList);
                    //计算价格
                    float totalPrice = 0.0f;
                    if (goodsInfoList != null && goodsInfoList.size() > 0) {
                        for (int j = 0; j < goodsInfoList.size(); j++) {
                            DatabaseGoodsInfo databaseGoodsInfo = goodsInfoList.get(j);
                            if (databaseGoodsInfo.getIsSelect()) {
                                totalPrice = BigDecimalUtil.add(totalPrice, databaseGoodsInfo.getGoodsNumber() * databaseGoodsInfo.getGoodsPriceVip()).floatValue();
                            }
                        }
                    }
                    shopInfo.setShopTotalPrice(totalPrice);

                    if (shopInfo.getShopId().equals(locationShopId)) {
                        //将当前店铺放倒第一个
                        databaseShopInfoList.add(0, shopInfo);
                    } else {
//                        直接添加
                        databaseShopInfoList.add(shopInfo);
                    }
                }

            }
            Logger.i("tong_database", "查询购物车:" + new Gson().toJson(databaseShopInfoList));
            return databaseShopInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 数据插入到本地数据库：同步数据到本地数据库
    public void insertDatabaseData(List<CartShopInfoBean> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                //添加店铺
                CartShopInfoBean shopInfoBean = list.get(i);
                addShopData(shopInfoBean);
                //商品
                List<CartShopInfoBean.ListBean> listBeanList = shopInfoBean.getList();
                String shopId = shopInfoBean.getShopId() + "";
                if (listBeanList != null && listBeanList.size() > 0) {
                    for (int j = 0; j < listBeanList.size(); j++) {
                        CartShopInfoBean.ListBean listBean = listBeanList.get(j);
                        //更改库存
                        if(listBean.getIsTemai()==0){
                            if (listBean.getSpecialSale() == 0 && listBean.getStock() > 0) {
                                listBean.setStock(1);
                            } else {
                                listBean.setStock(0);
                            }
                        }

                        //添加商品信息
                        addGoodsData(shopId, listBean);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 更新购物车数据：用于购物车页面
    public List<DatabaseShopInfo> updateGoodsCartDatabaseData(String locationShopId, List<CartShopInfoBean> list) {//网络请求的数据
        try {
            //当前数据库的数据
            List<DatabaseGoodsInfo> currentGoodsDataList = queryAllGoods();

            if (currentGoodsDataList == null || currentGoodsDataList.size() == 0) {
                //本地没有数据的情况
                insertDatabaseData(list);
            } else if (list == null || list.size() == 0) {
                //清空当前本地数据库中的商品数据
                clearDatabase();
            } else {
                Logger.t("本地数据库商品：" + new Gson().toJson(currentGoodsDataList));

                List<CartShopInfoBean.ListBean> allPostGoodsList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    //店铺数据
                    CartShopInfoBean cartShopInfoBean = list.get(i);

                    //商品列表数据
                    List<CartShopInfoBean.ListBean> goodsList = cartShopInfoBean.getList();
                    int shopId = cartShopInfoBean.getShopId();
                    //添加当前没有的数据
                    if (goodsList != null && goodsList.size() > 0) {
                        isNewAdd = false;
                        for (int j = 0; j < goodsList.size(); j++) {
                            CartShopInfoBean.ListBean listBean = goodsList.get(j);
                            listBean.setShopId(shopId);
                            //更改库存
                            if(listBean.getIsTemai()==0){
                                if (listBean.getSpecialSale() == 0 && listBean.getStock() > 0) {
                                    listBean.setStock(1);
                                } else {
                                    listBean.setStock(0);
                                }
                            }
                            //添加并刷新商品信息
                            addGoodsData(shopId + "", listBean);
                        }
                        allPostGoodsList.addAll(goodsList);
                    }

                    //添加店铺
                    addShopData(cartShopInfoBean);
                }

                //有店铺必有商品，(可以不需要判断空)
                // 去除多余的数据
                int size = allPostGoodsList.size();
                for (int j = 0; j < currentGoodsDataList.size(); j++) {
                    DatabaseGoodsInfo databaseGoodsInfo = currentGoodsDataList.get(j);
                    int mTotal = 0;
                    e:
                    for (int m = 0; m < size; m++) {
                        CartShopInfoBean.ListBean listBean = allPostGoodsList.get(m);
                        //判断不相同的商品
                        if (databaseGoodsInfo.getShopId().equals(listBean.getShopId() + "")
                                && databaseGoodsInfo.getGoodsId().equals(listBean.getGoodsId() + "")
                                && databaseGoodsInfo.getSpecId().equals(listBean.getAttrStrId())
                        ) {
                            //存在,需要更新参数
                            break e;
                        } else {
                            mTotal++;
                        }
                    }
                    if (mTotal == size) {
                        //不存在
                        //删除操作
                        goodsInfoDao.deleteByKey(databaseGoodsInfo.getId());
                    }
                }

            }
//            List<DatabaseGoodsInfo> goodsInfoList = daoSession.loadAll(DatabaseGoodsInfo.class);
//            List<DatabaseShopInfo> shopInfoList = daoSession.loadAll(DatabaseShopInfo.class);
//            Logger.t("商品：" + (goodsInfoList == null ? 0 : goodsInfoList.size()));
//            Logger.t("商铺：" + (shopInfoList == null ? 0 : shopInfoList.size()));
            return queryShoppingCartData(locationShopId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addShopData(CartShopInfoBean shopInfoBean) {
        try {
            DatabaseShopInfo databaseShopInfo = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopInfoBean.getShopId() + "")).unique();
            if (databaseShopInfo == null) {
                databaseShopInfo = new DatabaseShopInfo();
                databaseShopInfo.setId("id" + System.currentTimeMillis());
                databaseShopInfo.setShopId(shopInfoBean.getShopId() + "");
                databaseShopInfo.setShopName(shopInfoBean.getShopName());
                databaseShopInfo.setShopAddress(shopInfoBean.getShopAddress());//店铺地址
                databaseShopInfo.setShopLa(shopInfoBean.getShopLatitude());
                databaseShopInfo.setShopLo(shopInfoBean.getShopLongitude());
                databaseShopInfo.setShopArea(shopInfoBean.getDeliveryScope());
                databaseShopInfo.setShopIntro(shopInfoBean.getShopIntro());
                databaseShopInfo.setShopTotalPrice(0.0f);
                databaseShopInfo.setIsAllSelect(false);
                Logger.i("tong_database", "addShopData : insert shop start ：");
                shopInfoDao.insert(databaseShopInfo);
                Logger.i("tong_database", "addShopData : insert shop end ：");
            } else {
                Logger.i("tong_database", "addShopData : shop is exist ");
                Logger.i("tong_database", "addShopData : update shop start");
                databaseShopInfo.setShopName(shopInfoBean.getShopName());
                databaseShopInfo.setShopAddress(shopInfoBean.getShopAddress());
                databaseShopInfo.setShopArea(shopInfoBean.getDeliveryScope());
                databaseShopInfo.setShopLa(shopInfoBean.getShopLatitude());
                databaseShopInfo.setShopLo(shopInfoBean.getShopLongitude());
                databaseShopInfo.setShopIntro(shopInfoBean.getShopIntro());
                if(isNewAdd){
                    //有新增加的数据：改变全选的状态
                    databaseShopInfo.setIsAllSelect(false);
                }
                shopInfoDao.update(databaseShopInfo);

                Logger.i("tong_database", "addShopData : update shop end");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNewAdd = false;
    private void addGoodsData(String shopId, CartShopInfoBean.ListBean listBean) {

        DatabaseGoodsInfo info = goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(listBean.getGoodsId() + "")
                , DatabaseGoodsInfoDao.Properties.ShopId.eq(shopId)
                , DatabaseGoodsInfoDao.Properties.SpecId.eq(listBean.getAttrStrId())
        ).unique();
        if (info == null) {
            isNewAdd = true;
            //添加购物车商品
            DatabaseGoodsInfo goodsInfo = new DatabaseGoodsInfo();
//        商品信息
            goodsInfo.setGoodsId(listBean.getGoodsId() + "");
            goodsInfo.setGoodsName(listBean.getGoodsName());
            goodsInfo.setGoodsPrice(listBean.getSellPrice().floatValue());
            goodsInfo.setGoodsPriceVip(listBean.getSellPriceDiscount().floatValue());
            goodsInfo.setGoodsNumber(listBean.getTotalNum());
            goodsInfo.setGoodsUrl(listBean.getGoodsImage());
            goodsInfo.setGoodsTotal(listBean.getStock());
            //规格
            goodsInfo.setSpecId(listBean.getAttrStrId());
            goodsInfo.setSpecName(listBean.getAttrStrValue());
            goodsInfo.setIsTemai(listBean.getIsTemai());
            goodsInfo.setSpecialSale(listBean.getSpecialSale());
            //分类类型
            goodsInfo.setCatid(listBean.getCatid());
            goodsInfo.setCnname(listBean.getCnname());
            goodsInfo.setStock(listBean.getStock());
            //商品类型
            int goodsType = listBean.getGoodsType();
            goodsInfo.setGoodsType(goodsType);
            //预售商品
            if (goodsType == 3 && listBean.getPresell() != null) {
                goodsInfo.setStartTime(listBean.getPresell().getStartTime());
                goodsInfo.setEndTime(listBean.getPresell().getEndTime());
                goodsInfo.setGoodsTotal(listBean.getPresell().getLimitNum());
            }

            long time = System.currentTimeMillis();
            goodsInfo.setId("id" + time);
//        添加时间
            goodsInfo.setGoodsAddTime(listBean.getAddTime());
//        店铺信息
            goodsInfo.setShopId(shopId);

            Logger.i("tong_database", "addGoodsData : goods start insert");
            goodsInfoDao.insert(goodsInfo);
            Logger.i("tong_database", "addGoodsData : goods end insert");
        } else {
            Logger.i("tong_database", "addGoodsData : goods is exist ");
            Logger.i("tong_database", "addGoodsData : update goods start");
            //存在商品，更新属性值
            info.setGoodsName(listBean.getGoodsName());
            info.setGoodsPrice(listBean.getSellPrice().floatValue());
            info.setGoodsPriceVip(listBean.getSellPriceDiscount().floatValue());
            info.setGoodsNumber(listBean.getTotalNum());
            info.setGoodsUrl(listBean.getGoodsImage());
            info.setStock(listBean.getStock());

            int goodsType = listBean.getGoodsType();
            //预售商品
            if (goodsType == 3 && listBean.getPresell() != null) {
                info.setStartTime(listBean.getPresell().getStartTime());
                info.setEndTime(listBean.getPresell().getEndTime());
                info.setGoodsTotal(listBean.getPresell().getLimitNum());
            } else {
                info.setGoodsTotal(listBean.getStock());
            }
            info.setIsTemai(listBean.getIsTemai());
            info.setSpecialSale(listBean.getSpecialSale());
            //规格
            info.setSpecId(listBean.getAttrStrId());
            info.setSpecName(listBean.getAttrStrValue());
            //        店铺信息
            info.setShopId(shopId);
            goodsInfoDao.update(info);
            Logger.i("tong_database", "addGoodsData : update goods end");
        }

    }


    /**
     * 插入定位的店铺:在切换店铺的时候插入
     *
     * @param shopInfoBean 定位店铺的信息
     */
    public void insertShop(ShopInfoBean shopInfoBean) {
        try {
            DatabaseShopInfo databaseShopInfo = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopInfoBean.getShopId() + "")).unique();
            if (databaseShopInfo == null) {
                databaseShopInfo = new DatabaseShopInfo();
                databaseShopInfo.setId("id" + System.currentTimeMillis());
                databaseShopInfo.setShopId(shopInfoBean.getShopId() + "");
                databaseShopInfo.setShopName(shopInfoBean.getShopName());
                databaseShopInfo.setShopAddress(shopInfoBean.getShopAddress());
                databaseShopInfo.setShopLa(Double.parseDouble(shopInfoBean.getShopLatitude()));
                databaseShopInfo.setShopLo(Double.parseDouble(shopInfoBean.getShopLongitude()));
                databaseShopInfo.setShopArea(shopInfoBean.getDeliveryScope());
                databaseShopInfo.setShopIntro(shopInfoBean.getShopIntro());
                databaseShopInfo.setShopTotalPrice(0.0f);
                databaseShopInfo.setIsAllSelect(false);
                Logger.i("tong_database", "insertShop : insert shop start ：" + new Gson().toJson(databaseShopInfo));
                shopInfoDao.insert(databaseShopInfo);
                Logger.i("tong_database", "insertShop : insert shop end ：" + new Gson().toJson(databaseShopInfo));
            } else {
                Logger.i("tong_database", "insertShop : shop is exist ");
                Logger.i("tong_database", "insertShop : update shop start");
                databaseShopInfo.setShopName(shopInfoBean.getShopName());
                databaseShopInfo.setShopAddress(shopInfoBean.getShopIntro());
                databaseShopInfo.setShopArea(shopInfoBean.getDeliveryScope());
                databaseShopInfo.setShopLa(Double.parseDouble(shopInfoBean.getShopLatitude()));
                databaseShopInfo.setShopLo(Double.parseDouble(shopInfoBean.getShopLongitude()));
                databaseShopInfo.setShopIntro(shopInfoBean.getShopIntro());
                shopInfoDao.update(databaseShopInfo);
                Logger.i("tong_database", "insertShop : update shop end");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //插入商品
    public void insertGoods(String locationShopId, DatabaseGoodsInfo goodsInfo, OnChangeDatabaseListener listener) {
        try {
            long time = System.currentTimeMillis();
            goodsInfo.setId("id" + time);
//        添加时间
            goodsInfo.setGoodsAddTime(time);
            DatabaseGoodsInfo info = goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(goodsInfo.getGoodsId())
                    , DatabaseGoodsInfoDao.Properties.ShopId.eq(locationShopId)
                    , DatabaseGoodsInfoDao.Properties.SpecId.eq(goodsInfo.getSpecId())
            ).unique();

            if (info == null) {
                Logger.i("tong_database", "insertGoods : start insert");
                goodsInfoDao.insert(goodsInfo);
                //更新选择状态
                DatabaseShopInfo databaseShopInfo = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(locationShopId)).unique();
                if (databaseShopInfo != null && databaseShopInfo.getIsAllSelect()) {
                    databaseShopInfo.setIsAllSelect(false);
                    shopInfoDao.update(databaseShopInfo);
                }
                if (listener != null) {
                    listener.isChangeDatabase(true);
                }
            } else {
                Logger.i("tong_database", "insertGoods : data exist");
                updateGoodsNumber(goodsInfo, goodsInfo.getGoodsNumber(), locationShopId, listener);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.i("tong_database", "insertGoods : insert exception ：" + e.getMessage());
        }

    }

    //    更新数量
    public void updateGoodsNumber(DatabaseGoodsInfo goodsInfo, int number, String shopId, OnChangeDatabaseListener listener) {
        try {
            DatabaseGoodsInfo info = goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(goodsInfo.getGoodsId())
                    , DatabaseGoodsInfoDao.Properties.ShopId.eq(goodsInfo.getShopId())
                    , DatabaseGoodsInfoDao.Properties.SpecId.eq(goodsInfo.getSpecId())
            ).unique();

            if (info != null) {
                //通过Key来删除，这里的Key就是user字段中的ID号
                info.setGoodsNumber(info.getGoodsNumber() + number);
                goodsInfoDao.update(info);
                Logger.i("tong_database", "更新数量:" + info.getGoodsNumber());
                if (listener != null) {
                    listener.isChangeDatabase(true);
                }
                if (info.getIsSelect()) {
                    DatabaseShopInfo databaseShopInfo = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopId)).unique();
                    //                    改变总价格
                    float totalPrice = databaseShopInfo.getShopTotalPrice();
                    totalPrice = BigDecimalUtil.add(totalPrice, number * goodsInfo.getGoodsPriceVip()).floatValue();
                    databaseShopInfo.setShopTotalPrice(totalPrice);
                    shopInfoDao.update(databaseShopInfo);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //更新价格
    public void updateGoodsPrice(DatabaseGoodsInfo goodsInfo, String shopId, OnChangeDatabaseListener listener) {
        try {
            DatabaseGoodsInfo info = goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(goodsInfo.getGoodsId())
                    , DatabaseGoodsInfoDao.Properties.ShopId.eq(goodsInfo.getShopId())
                    , DatabaseGoodsInfoDao.Properties.SpecId.eq(goodsInfo.getSpecId())
            ).unique();

            if (info != null) {
                //通过Key来删除，这里的Key就是user字段中的ID号
                float chazhi = goodsInfo.getGoodsPriceVip() - info.getGoodsPriceVip();
                info.setGoodsPriceVip(goodsInfo.getGoodsPriceVip());
                goodsInfoDao.update(info);
                Logger.i("tong_database", "更新价格:" + info.getGoodsPriceVip());
                if (listener != null) {
                    listener.isChangeDatabase(true);
                }
                if (info.getIsSelect()) {
                    DatabaseShopInfo databaseShopInfo = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopId)).unique();
                    //                    改变总价格
                    float totalPrice = databaseShopInfo.getShopTotalPrice();
                    totalPrice = BigDecimalUtil.add(totalPrice, goodsInfo.getGoodsNumber() * chazhi).floatValue();
                    databaseShopInfo.setShopTotalPrice(totalPrice);
                    shopInfoDao.update(databaseShopInfo);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新店铺选择的状态
     *
     * @param shopInfo
     */
    public void updateShopSelectState(DatabaseShopInfo shopInfo) {
        try {
            DatabaseShopInfo info = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopInfo.getShopId())).unique();
            if (info != null) {
                info.setIsAllSelect(shopInfo.getIsAllSelect());
                ArrayList<DatabaseGoodsInfo> arrayList = shopInfo.getGoodsInfoList();
                float totalPrice = 0.0f;
                for (int i = 0; i < arrayList.size(); i++) {
                    DatabaseGoodsInfo goodsInfo = arrayList.get(i);
                    goodsInfo.setIsSelect(shopInfo.getIsAllSelect());

                    DatabaseGoodsInfo databaseGoodsInfo = goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(goodsInfo.getGoodsId())
                            , DatabaseGoodsInfoDao.Properties.ShopId.eq(goodsInfo.getShopId())
                            , DatabaseGoodsInfoDao.Properties.SpecId.eq(goodsInfo.getSpecId())
                    ).unique();
                    if (databaseGoodsInfo != null) {
                        databaseGoodsInfo.setIsSelect(goodsInfo.getIsSelect());
                        goodsInfoDao.update(databaseGoodsInfo);
                        Logger.i("tong_database", "更新商品选择状态成功:");
                        //                    改变总价格
                        if (databaseGoodsInfo.getIsSelect()) {
                            totalPrice = BigDecimalUtil.add(totalPrice, goodsInfo.getGoodsNumber() * goodsInfo.getGoodsPriceVip()).floatValue();
                        }/* else {
                            totalPrice = BigDecimalUtil.sub(totalPrice, goodsInfo.getGoodsNumber() * goodsInfo.getGoodsPrice()).floatValue();
                        }*/
                    }
                }
                shopInfo.setShopTotalPrice(totalPrice);
                info.setShopTotalPrice(totalPrice);
                shopInfoDao.update(info);
                Logger.i("tong_database", "更新店铺选择状态成功:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新商品选择的状态
     *
     * @param shopInfo
     * @param goodsInfo
     */
    public void updateGoodsSelectState(DatabaseShopInfo shopInfo, DatabaseGoodsInfo goodsInfo) {
        try {
            DatabaseGoodsInfo info = goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(goodsInfo.getGoodsId())
                    , DatabaseGoodsInfoDao.Properties.ShopId.eq(goodsInfo.getShopId())
                    , DatabaseGoodsInfoDao.Properties.SpecId.eq(goodsInfo.getSpecId())
            ).unique();
            if (info != null) {
                info.setIsSelect(goodsInfo.getIsSelect());
                goodsInfoDao.update(info);
                Logger.i("tong_database", "更新商品选择状态成功:");
                //                    改变总价格
//                float totalPrice = BigDecimalUtil.add(shopInfo.getShopTotalPrice(), goodsInfo.getGoodsNumber() * goodsInfo.getGoodsPrice()).floatValue();
//                shopInfo.setShopTotalPrice(totalPrice);
                float totalPrice = shopInfo.getShopTotalPrice();
                if (info.getIsSelect()) {
                    totalPrice = BigDecimalUtil.add(totalPrice, goodsInfo.getGoodsNumber() * goodsInfo.getGoodsPriceVip()).floatValue();
                } else {
                    totalPrice = BigDecimalUtil.sub(totalPrice, goodsInfo.getGoodsNumber() * goodsInfo.getGoodsPriceVip()).floatValue();
                }
                shopInfo.setShopTotalPrice(totalPrice);
                //更新店铺的总价格
                DatabaseShopInfo databaseShopInfo = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopInfo.getShopId())).unique();
                databaseShopInfo.setShopTotalPrice(totalPrice);
                shopInfoDao.update(databaseShopInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除商品: 删除完成重新查询
    public ReqCartDeleteInfo deleteGoods(List<DatabaseShopInfo> infoList, int position, OnChangeDatabaseListener listener) {
        try {
            ReqCartDeleteInfo deleteInfo = new ReqCartDeleteInfo();

            DatabaseShopInfo shopInfo = infoList.get(position);
            shopInfo.setIsAllSelect(false);
            List<DatabaseGoodsInfo> goodsInfoList = shopInfo.getGoodsInfoList();
            if (goodsInfoList != null && goodsInfoList.size() > 0) {

                List<ReqCartDeleteInfo.ReqCartDeleteInfoBean> deleteInfoBeanList = new ArrayList<>();

                Iterator<DatabaseGoodsInfo> iterator = goodsInfoList.iterator();
                while (iterator.hasNext()) {
                    DatabaseGoodsInfo goodsInfoIn = iterator.next();
                    //删除选择的项
                    if (goodsInfoIn != null && goodsInfoIn.getIsSelect()) {
                        ReqCartDeleteInfo.ReqCartDeleteInfoBean deleteInfoBean = new ReqCartDeleteInfo.ReqCartDeleteInfoBean();
                        deleteInfoBean.setGoodsId(goodsInfoIn.getGoodsId());
                        deleteInfoBean.setAttrStrId(goodsInfoIn.getSpecId());
                        deleteInfoBeanList.add(deleteInfoBean);
                        //通过Key来删除，这里的Key就是user字段中的ID号
                        goodsInfoDao.deleteByKey(goodsInfoIn.getId());
//                    改变总价格
                        shopInfo.setShopTotalPrice(BigDecimalUtil.sub(shopInfo.getShopTotalPrice(), goodsInfoIn.getGoodsNumber() * goodsInfoIn.getGoodsPriceVip()).floatValue());
                        //删除数据
                        iterator.remove();
                    }
                }
                if (shopInfo.getIsAllSelect()) {

                    DatabaseShopInfo info = shopInfoDao.queryBuilder().where(DatabaseShopInfoDao.Properties.ShopId.eq(shopInfo.getShopId())).unique();
                    if (info != null) {
                        info.setIsAllSelect(shopInfo.getIsAllSelect());
                        info.setShopTotalPrice(shopInfo.getShopTotalPrice());
                        shopInfoDao.update(info);
                        Logger.i("tong_database", "更新店铺选择状态成功:");
                    }
                }
                if (listener != null) {
                    listener.isChangeDatabase(true);
                }
                deleteInfo.setGoods(deleteInfoBeanList);

            }
            //移除没有商品的店铺
            if (goodsInfoList == null || goodsInfoList.size() == 0) {
                infoList.remove(position);
            }

            return deleteInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //购物车商品数
    public long queryGoodsTypeNumber() {
        try {
            Logger.i("tong_database", "queryGoodsTypeNumber : start query goods_type");
            long number = goodsInfoDao.count();
//        long number = goodsInfoDao.queryBuilder().count();
            Logger.i("tong_database", "queryGoodsTypeNumber : query success goods_type");
            return number;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public DatabaseGoodsInfo queryGoodsInfo(String goodsId, String shopId, String specId) {
        try {
           return goodsInfoDao.queryBuilder().where(DatabaseGoodsInfoDao.Properties.GoodsId.eq(goodsId)
                    , DatabaseGoodsInfoDao.Properties.ShopId.eq(shopId)
                    , DatabaseGoodsInfoDao.Properties.SpecId.eq(specId)).unique();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


//    ------------------------------------------------ 地址搜索存储操作---------------------------------------

    /**
     * 查询当前有多少条
     *
     * @return
     */
    public long queryCityInfoNumber() {
        try {
            Logger.i("tong_database", "queryCityInfoNumber : start query cityinfo");
            long number = goodsInfoDao.count();
            Logger.i("tong_database", "queryCityInfoNumber : query success cityinfo");
            return number;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<DatabaseCityInfo> queryCityInfo() {
        try {
            ArrayList<DatabaseCityInfo> goodsInfoList = (ArrayList<DatabaseCityInfo>) cityInfoDao.queryBuilder().orderDesc(DatabaseCityInfoDao.Properties.AddTime).limit(8).list();
            Logger.i("tong_database", "查询最近搜索城市数据:" + (goodsInfoList == null ? 0 : goodsInfoList.size()));
            return goodsInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DatabaseCityInfo queryCityInfo(String id) {
        try {
            return cityInfoDao.queryBuilder().where(DatabaseCityInfoDao.Properties.Id.eq(id)).unique();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //    添加搜索城市数据

    /**
     *
     * @param cityInfo
     * @return 行数据id
     */
    public void insertCityInfoData(DatabaseCityInfo cityInfo) {
        try{
            DatabaseCityInfo info = cityInfoDao.queryBuilder().where(DatabaseCityInfoDao.Properties.Province.eq(cityInfo.getProvince())
                    , DatabaseCityInfoDao.Properties.City.eq(cityInfo.getCity())
                    , DatabaseCityInfoDao.Properties.District.eq(cityInfo.getDistrict())
                    , DatabaseCityInfoDao.Properties.AddressDetail.eq(cityInfo.getAddressDetail())
            ).unique();

            long time = System.currentTimeMillis();

            if (info == null) {
                cityInfo.setId("id" + time);
                //        添加时间
                cityInfo.setAddTime(time);
                Logger.i("tong_database", "insertCityInfoData : cityinfo start insert");
                cityInfoDao.insert(cityInfo);
                Logger.i("tong_database", "insertCityInfoData : cityinfo end insert");
            } else {
                Logger.i("tong_database", "insertCityInfoData : cityinfo is exist ");
                Logger.i("tong_database", "insertCityInfoData : update cityinfo start");
                //        更新时间
                info.setAddTime(time);
                cityInfoDao.update(info);
                Logger.i("tong_database", "insertCityInfoData : update cityinfo end");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.i("tong_database", "insertCityInfoData  exception : " + e.getMessage());
        }
    }

    public void updataCityInfoData(String id) {
        try {
            DatabaseCityInfo info = queryCityInfo(id);
            Logger.i("tong_database", "updataCityInfoData : update cityinfo start");
            //  更新时间
            info.setAddTime(System.currentTimeMillis());
            cityInfoDao.update(info);
            Logger.i("tong_database", "updataCityInfoData : update cityinfo end");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //    删除数据库数据
    public void clearCityInfoDatabase() {
        try {
            daoSession.deleteAll(DatabaseCityInfo.class);
            Logger.i("tong_database", "清除地址搜索历史数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.i("tong_database", "清除地址搜索历史数据失败：" + e.getMessage());
        }
    }


    //    ------------------------------------------------ 商品搜索存储操作---------------------------------------
    public List<DatabaseSearchGoodsInfo> querySearchGoodsInfo() {
        try {
            List<DatabaseSearchGoodsInfo> goodsInfoList = searchGoodsInfoDao.queryBuilder().orderDesc(DatabaseSearchGoodsInfoDao.Properties.AddTime).limit(8).list();
            Logger.i("tong_database", "查询最近搜索商品数据:" + (goodsInfoList == null ? 0 : goodsInfoList.size()));
            return goodsInfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DatabaseSearchGoodsInfo querySearchGoodsInfo(String id) {
        try {
            return searchGoodsInfoDao.queryBuilder().where(DatabaseSearchGoodsInfoDao.Properties.Id.eq(id)).unique();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertSearchGoodsInfoData(String searchStr) {
        try{
            DatabaseSearchGoodsInfo info = searchGoodsInfoDao.queryBuilder().where(DatabaseSearchGoodsInfoDao.Properties.SearchName.eq(searchStr)).unique();

            long time = System.currentTimeMillis();

            if (info == null) {
                DatabaseSearchGoodsInfo searchGoodsInfo = new DatabaseSearchGoodsInfo();
                searchGoodsInfo.setId("id" + time);
                //        添加时间
                searchGoodsInfo.setAddTime(time);
                searchGoodsInfo.setSearchName(searchStr);

                Logger.i("tong_database", "insertSearchGoodsInfoData : searchgoodsinfo start insert");
                searchGoodsInfoDao.insert(searchGoodsInfo);
                Logger.i("tong_database", "insertSearchGoodsInfoData : searchgoodsinfo end insert");
            } else {
                Logger.i("tong_database", "insertSearchGoodsInfoData : searchgoodsinfo is exist ");
                Logger.i("tong_database", "insertSearchGoodsInfoData : update searchgoodsinfo start");
                //        更新时间
                info.setAddTime(time);
                searchGoodsInfoDao.update(info);
                Logger.i("tong_database", "insertSearchGoodsInfoData : update searchgoodsinfo end");
            }
        }catch (Exception e){
            e.printStackTrace();
            Logger.i("tong_database", "insertSearchGoodsInfoData  exception : " + e.getMessage());
        }
    }

    public void updataSearchGoodsInfoData(String id) {
        try {
            DatabaseSearchGoodsInfo info = querySearchGoodsInfo(id);
            Logger.i("tong_database", "updataSearchGoodsInfoData : update searchgoodsinfo start");
            //  更新时间
            info.setAddTime(System.currentTimeMillis());
            searchGoodsInfoDao.update(info);
            Logger.i("tong_database", "updataSearchGoodsInfoData : update searchgoodsinfo end");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //    删除数据库数据
    public void clearSearchGoodsInfoDatabase() {
        try {
            daoSession.deleteAll(DatabaseSearchGoodsInfo.class);
            Logger.i("tong_database", "清除商品搜索历史数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.i("tong_database", "清除商品搜索历史数据失败：" + e.getMessage());
        }
    }

}
