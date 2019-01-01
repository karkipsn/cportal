
package com.example.colors2web.clientportal.POJO.Offices;

import com.google.gson.annotations.SerializedName;


public class CityBin {

    @SerializedName("address1")
    private String mAddress1;
    @SerializedName("address2")
    private String mAddress2;
    @SerializedName("bin")
    private String mBin;
    @SerializedName("city")
    private String mCity;
    @SerializedName("company_email")
    private String mCompanyEmail;
    @SerializedName("company_name")
    private String mCompanyName;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("manager1_email")
    private String mManager1Email;
    @SerializedName("manager1_name")
    private String mManager1Name;
    @SerializedName("manager2_email")
    private String mManager2Email;
    @SerializedName("manager2_name")
    private String mManager2Name;
    @SerializedName("ship_to_name")
    private String mShipToName;
    @SerializedName("state")
    private String mState;
    @SerializedName("zip")
    private String mZip;

    public String getAddress1() {
        return mAddress1;
    }

    public void setAddress1(String address1) {
        mAddress1 = address1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public void setAddress2(String address2) {
        mAddress2 = address2;
    }

    public String getBin() {
        return mBin;
    }

    public void setBin(String bin) {
        mBin = bin;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCompanyEmail() {
        return mCompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        mCompanyEmail = companyEmail;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getManager1Email() {
        return mManager1Email;
    }

    public void setManager1Email(String manager1Email) {
        mManager1Email = manager1Email;
    }

    public String getManager1Name() {
        return mManager1Name;
    }

    public void setManager1Name(String manager1Name) {
        mManager1Name = manager1Name;
    }

    public String getManager2Email() {
        return mManager2Email;
    }

    public void setManager2Email(String manager2Email) {
        mManager2Email = manager2Email;
    }

    public String getManager2Name() {
        return mManager2Name;
    }

    public void setManager2Name(String manager2Name) {
        mManager2Name = manager2Name;
    }

    public String getShipToName() {
        return mShipToName;
    }

    public void setShipToName(String shipToName) {
        mShipToName = shipToName;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

}
