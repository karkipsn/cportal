
package com.example.colors2web.clientportal.POJO.CustomerDetails;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCustomers {

    @SerializedName("customer")
    private Customer mCustomer;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("shipStationCredential")
    private ShipStationCredential mShipStationCredential;
    @SerializedName("shippingMethods")
    private List<ShippingMethod> mShippingMethods;
    @SerializedName("shopifyCredential")
    private ShopifyCredential mShopifyCredential;

    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer customer) {
        mCustomer = customer;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

    public ShipStationCredential getShipStationCredential() {
        return mShipStationCredential;
    }

    public void setShipStationCredential(ShipStationCredential shipStationCredential) {
        mShipStationCredential = shipStationCredential;
    }

    public List<ShippingMethod> getShippingMethods() {
        return mShippingMethods;
    }

    public void setShippingMethods(List<ShippingMethod> shippingMethods) {
        mShippingMethods = shippingMethods;
    }

    public ShopifyCredential getShopifyCredential() {
        return mShopifyCredential;
    }

    public void setShopifyCredential(ShopifyCredential shopifyCredential) {
        mShopifyCredential = shopifyCredential;
    }

}
