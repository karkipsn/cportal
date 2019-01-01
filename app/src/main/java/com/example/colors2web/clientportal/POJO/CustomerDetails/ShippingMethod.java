
package com.example.colors2web.clientportal.POJO.CustomerDetails;

import com.google.gson.annotations.SerializedName;

public class ShippingMethod {

    @SerializedName("account_number")
    private String mAccountNumber;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("created_by")
    private Long mCreatedBy;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("id")
    private Long mId;
    @SerializedName("international_account_number")
    private String mInternationalAccountNumber;
    @SerializedName("shipping_account_type")
    private String mShippingAccountType;
    @SerializedName("shipping_company_type")
    private String mShippingCompanyType;
    @SerializedName("tax_account_number")
    private String mTaxAccountNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("updated_by")
    private Long mUpdatedBy;

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        mAccountNumber = accountNumber;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(Long createdBy) {
        mCreatedBy = createdBy;
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

    public String getInternationalAccountNumber() {
        return mInternationalAccountNumber;
    }

    public void setInternationalAccountNumber(String internationalAccountNumber) {
        mInternationalAccountNumber = internationalAccountNumber;
    }

    public String getShippingAccountType() {
        return mShippingAccountType;
    }

    public void setShippingAccountType(String shippingAccountType) {
        mShippingAccountType = shippingAccountType;
    }

    public String getShippingCompanyType() {
        return mShippingCompanyType;
    }

    public void setShippingCompanyType(String shippingCompanyType) {
        mShippingCompanyType = shippingCompanyType;
    }

    public String getTaxAccountNumber() {
        return mTaxAccountNumber;
    }

    public void setTaxAccountNumber(String taxAccountNumber) {
        mTaxAccountNumber = taxAccountNumber;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return mUpdatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        mUpdatedBy = updatedBy;
    }

}
