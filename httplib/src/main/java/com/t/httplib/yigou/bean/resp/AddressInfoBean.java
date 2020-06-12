package com.t.httplib.yigou.bean.resp;

import com.t.httplib.yigou.bean.req.ReqTimeInfo;

import java.io.Serializable;

/**
 * 地址列表的Bean
 */
public class AddressInfoBean extends ReqTimeInfo implements Serializable {

    //    数据响应的id
    private String id;
    //    数据请求的id
    private String addressId;
    private String name;
    // 0 男 1 女
    private int sex;
    private String phone;
    private String province;
    private String city;
    private String county;
    private String address;
    private String detailAddress;
    // 是否默认地址
    private int isDefault;

    private double longitude;
    private double latitude;

    private long addTime;
//配送地址距离店铺的距离
    private double distance;
//配送范围状态：0不在范围；1在范围
    private int range;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    //选择View状态
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    //是否显示事件View
    private boolean isShowEventView;

    public boolean isShowEventView() {
        return isShowEventView;
    }

    public void setShowEventView(boolean showEventView) {
        isShowEventView = showEventView;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province == null ? "" : province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county == null ? "" : county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress == null ? "" : detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "address" + address +
                getAddressIdStr() +
                "county" + county +
                "city" + city +
                "detailAddress" + detailAddress +
//                ",isDefault=" + isDefault +
                "latitude" + latitude +
                "longitude" + longitude +
                "name" + name +
                "phone" + phone +
                "province" + province +
                "sex" + sex +
                "timestamp" + timestamp
                ;
    }

    private String getAddressIdStr() {
        if (addressId != null && !addressId.equals("")) {
            return "addressId" + addressId;
        }
        return "";
    }

}