
package com.example.colors2web.clientportal.POJO.InventoriesDeleveries.Deleveries;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDelivery {

    @SerializedName("drShipments")
    private List<DrShipment> mDrShipments;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    public List<DrShipment> getDrShipments() {
        return mDrShipments;
    }

    public void setDrShipments(List<DrShipment> drShipments) {
        mDrShipments = drShipments;
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

}
