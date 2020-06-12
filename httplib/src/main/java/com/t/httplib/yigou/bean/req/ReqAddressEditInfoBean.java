package com.t.httplib.yigou.bean.req;

import java.io.Serializable;

/**
 * 编辑地址请求的Bean
 */
public class ReqAddressEditInfoBean extends ReqTimeInfo implements Serializable {

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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
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
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
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
                "city" + city +
                "county" + county +
                "detailAddress" + detailAddress +
                "isDefault" + 0 +
                "latitude" + latitude +
                "longitude" + longitude +
                "name" + name +
                "phone" + phone +
                "province" + province +
                "sex" + 0 +
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
