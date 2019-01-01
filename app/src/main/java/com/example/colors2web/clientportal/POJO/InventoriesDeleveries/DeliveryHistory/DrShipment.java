
package com.example.colors2web.clientportal.POJO.InventoriesDeleveries.DeliveryHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DrShipment implements Serializable {

    @SerializedName("actual_box_count")
    private Long actualBoxCount;
    @SerializedName("actual_item_count")
    private Long actualItemCount;
    @SerializedName("actual_pallet_count")
    private Long actualPalletCount;
    @SerializedName("all_items_moved_to_pick")
    private Long allItemsMovedToPick;
    @SerializedName("confirm_dr_note")
    private String confirmDrNote;
    @SerializedName("contact_email")
    private String contactEmail;
    @SerializedName("contact_name")
    private String contactName;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("created_by")
    private Long createdBy;
    @SerializedName("customer_id")
    private Long customerId;
    @SerializedName("customer_item_id")
    private Long customerItemId;
    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("dr_description")
    private String drDescription;
    @SerializedName("dr_number")
    private Long drNumber;
    @SerializedName("dr_type")
    private String drType;
    @SerializedName("est_delivery_date")
    private String estDeliveryDate;
    @Expose
    private Long id;
    @SerializedName("isNon_confirm_dr")
    private String isNonConfirmDr;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_sku_number")
    private String itemSkuNumber;
    @SerializedName("queue_type")
    private String queueType;
    @SerializedName("said_box_count")
    private Long saidBoxCount;
    @SerializedName("said_item_count")
    private Long saidItemCount;
    @SerializedName("said_pallet_count")
    private Long saidPalletCount;
    @SerializedName("service_status")
    private String serviceStatus;
    @SerializedName("tracking_number")
    private String trackingNumber;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("updated_by")
    private Long updatedBy;
    @SerializedName("urgency_status")
    private String urgencyStatus;

    public Long getActualBoxCount() {
        return actualBoxCount;
    }

    public void setActualBoxCount(Long actualBoxCount) {
        this.actualBoxCount = actualBoxCount;
    }

    public Long getActualItemCount() {
        return actualItemCount;
    }

    public void setActualItemCount(Long actualItemCount) {
        this.actualItemCount = actualItemCount;
    }

    public Long getActualPalletCount() {
        return actualPalletCount;
    }

    public void setActualPalletCount(Long actualPalletCount) {
        this.actualPalletCount = actualPalletCount;
    }

    public Long getAllItemsMovedToPick() {
        return allItemsMovedToPick;
    }

    public void setAllItemsMovedToPick(Long allItemsMovedToPick) {
        this.allItemsMovedToPick = allItemsMovedToPick;
    }

    public String getConfirmDrNote() {
        return confirmDrNote;
    }

    public void setConfirmDrNote(String confirmDrNote) {
        this.confirmDrNote = confirmDrNote;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerItemId() {
        return customerItemId;
    }

    public void setCustomerItemId(Long customerItemId) {
        this.customerItemId = customerItemId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDrDescription() {
        return drDescription;
    }

    public void setDrDescription(String drDescription) {
        this.drDescription = drDescription;
    }

    public Long getDrNumber() {
        return drNumber;
    }

    public void setDrNumber(Long drNumber) {
        this.drNumber = drNumber;
    }

    public String getDrType() {
        return drType;
    }

    public void setDrType(String drType) {
        this.drType = drType;
    }

    public String getEstDeliveryDate() {
        return estDeliveryDate;
    }

    public void setEstDeliveryDate(String estDeliveryDate) {
        this.estDeliveryDate = estDeliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsNonConfirmDr() {
        return isNonConfirmDr;
    }

    public void setIsNonConfirmDr(String isNonConfirmDr) {
        this.isNonConfirmDr = isNonConfirmDr;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSkuNumber() {
        return itemSkuNumber;
    }

    public void setItemSkuNumber(String itemSkuNumber) {
        this.itemSkuNumber = itemSkuNumber;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public Long getSaidBoxCount() {
        return saidBoxCount;
    }

    public void setSaidBoxCount(Long saidBoxCount) {
        this.saidBoxCount = saidBoxCount;
    }

    public Long getSaidItemCount() {
        return saidItemCount;
    }

    public void setSaidItemCount(Long saidItemCount) {
        this.saidItemCount = saidItemCount;
    }

    public Long getSaidPalletCount() {
        return saidPalletCount;
    }

    public void setSaidPalletCount(Long saidPalletCount) {
        this.saidPalletCount = saidPalletCount;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUrgencyStatus() {
        return urgencyStatus;
    }

    public void setUrgencyStatus(String urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }

}
