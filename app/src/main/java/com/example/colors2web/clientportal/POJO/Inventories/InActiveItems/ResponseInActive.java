
package com.example.colors2web.clientportal.POJO.Inventories.InActiveItems;

import java.util.List;
import com.google.gson.annotations.Expose;


public class ResponseInActive {

    @Expose
    private String message;
    @Expose
    private String returnType;
    @Expose
    private List<StoreInactiveItem> storeInactiveItems;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<StoreInactiveItem> getStoreInactiveItems() {
        return storeInactiveItems;
    }

    public void setStoreInactiveItems(List<StoreInactiveItem> storeInactiveItems) {
        this.storeInactiveItems = storeInactiveItems;
    }

}
