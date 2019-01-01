
package com.example.colors2web.clientportal.POJO.Inventories.AllItems.UOM;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseUOM {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("UOMs")
    private List<UOM> mUOMs;

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

    public List<UOM> getUOMs() {
        return mUOMs;
    }

    public void setUOMs(List<UOM> uOMs) {
        mUOMs = uOMs;
    }

}
