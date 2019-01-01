
package com.example.colors2web.clientportal.POJO.Offices;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseOffice {

    @SerializedName("cityBins")
    private List<CityBin> mCityBins;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;

    public List<CityBin> getCityBins() {
        return mCityBins;
    }

    public void setCityBins(List<CityBin> cityBins) {
        mCityBins = cityBins;
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
