
package com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory;

import java.util.List;
import com.google.gson.annotations.Expose;


public class ResponseDrHistory {

    @Expose
    private List<DrShipment> drShipments;
    @Expose
    private String message;
    @Expose
    private String returnType;

    public List<DrShipment> getDrShipments() {
        return drShipments;
    }

    public void setDrShipments(List<DrShipment> drShipments) {
        this.drShipments = drShipments;
    }

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

}
