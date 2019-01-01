
package com.example.colors2web.clientportal.POJO.Inventories.InActiveItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StoreInactiveItem {

    @SerializedName("barcode_file_name")
    private String barcodeFileName;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("country_origin")
    private String countryOrigin;
    @SerializedName("cpg_sku_number")
    private String cpgSkuNumber;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("created_by")
    private Long createdBy;
    @SerializedName("customer_id")
    private Long customerId;
    @SerializedName("cycle_count_list")
    private String cycleCountList;
    @Expose
    private Long id;
    @SerializedName("is_cpg")
    private String isCpg;
    @SerializedName("is_lot_control")
    private String isLotControl;
    @SerializedName("is_low_inventory_notifications_sent")
    private Long isLowInventoryNotificationsSent;
    @SerializedName("is_over_size_product")
    private String isOverSizeProduct;
    @SerializedName("is_paused")
    private Long isPaused;
    @SerializedName("item_description")
    private String itemDescription;
    @SerializedName("item_image")
    private String itemImage;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_reorder_trigger")
    private Long itemReorderTrigger;
    @SerializedName("item_sku_number")
    private String itemSkuNumber;
    @SerializedName("item_status")
    private String itemStatus;
    @SerializedName("min_quantity")
    private Long minQuantity;
    @SerializedName("ordered_quantity")
    private String orderedQuantity;
    @Expose
    private String pick;
    @SerializedName("pick_balance")
    private Long pickBalance;
    @Expose
    private String price;
    @Expose
    private String replenish;
    @SerializedName("requested_quantity")
    private Object requestedQuantity;
    @SerializedName("special_program_number")
    private String specialProgramNumber;
    @SerializedName("tr_quantity")
    private Long trQuantity;
    @SerializedName("UOM")
    private String uOM;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("updated_by")
    private Long updatedBy;
    @Expose
    private String weight;
    @SerializedName("zummix_sku_number")
    private String zummixSkuNumber;

    public String getBarcodeFileName() {
        return barcodeFileName;
    }

    public void setBarcodeFileName(String barcodeFileName) {
        this.barcodeFileName = barcodeFileName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getCpgSkuNumber() {
        return cpgSkuNumber;
    }

    public void setCpgSkuNumber(String cpgSkuNumber) {
        this.cpgSkuNumber = cpgSkuNumber;
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

    public String getCycleCountList() {
        return cycleCountList;
    }

    public void setCycleCountList(String cycleCountList) {
        this.cycleCountList = cycleCountList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsCpg() {
        return isCpg;
    }

    public void setIsCpg(String isCpg) {
        this.isCpg = isCpg;
    }

    public String getIsLotControl() {
        return isLotControl;
    }

    public void setIsLotControl(String isLotControl) {
        this.isLotControl = isLotControl;
    }

    public Long getIsLowInventoryNotificationsSent() {
        return isLowInventoryNotificationsSent;
    }

    public void setIsLowInventoryNotificationsSent(Long isLowInventoryNotificationsSent) {
        this.isLowInventoryNotificationsSent = isLowInventoryNotificationsSent;
    }

    public String getIsOverSizeProduct() {
        return isOverSizeProduct;
    }

    public void setIsOverSizeProduct(String isOverSizeProduct) {
        this.isOverSizeProduct = isOverSizeProduct;
    }

    public Long getIsPaused() {
        return isPaused;
    }

    public void setIsPaused(Long isPaused) {
        this.isPaused = isPaused;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemReorderTrigger() {
        return itemReorderTrigger;
    }

    public void setItemReorderTrigger(Long itemReorderTrigger) {
        this.itemReorderTrigger = itemReorderTrigger;
    }

    public String getItemSkuNumber() {
        return itemSkuNumber;
    }

    public void setItemSkuNumber(String itemSkuNumber) {
        this.itemSkuNumber = itemSkuNumber;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Long getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Long minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(String orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public Long getPickBalance() {
        return pickBalance;
    }

    public void setPickBalance(Long pickBalance) {
        this.pickBalance = pickBalance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReplenish() {
        return replenish;
    }

    public void setReplenish(String replenish) {
        this.replenish = replenish;
    }

    public Object getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Object requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getSpecialProgramNumber() {
        return specialProgramNumber;
    }

    public void setSpecialProgramNumber(String specialProgramNumber) {
        this.specialProgramNumber = specialProgramNumber;
    }

    public Long getTrQuantity() {
        return trQuantity;
    }

    public void setTrQuantity(Long trQuantity) {
        this.trQuantity = trQuantity;
    }

    public String getUOM() {
        return uOM;
    }

    public void setUOM(String uOM) {
        this.uOM = uOM;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getZummixSkuNumber() {
        return zummixSkuNumber;
    }

    public void setZummixSkuNumber(String zummixSkuNumber) {
        this.zummixSkuNumber = zummixSkuNumber;
    }

}
