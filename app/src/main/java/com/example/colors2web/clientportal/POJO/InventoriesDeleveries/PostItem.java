package com.example.colors2web.clientportal.POJO.InventoriesDeleveries;

public class PostItem {

    String  item_sku;
    String  event_type;

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    String  from;
    String  to;
    String  customer_id;
    String  special_program;


    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSpecial_program() {
        return special_program;
    }

    public void setSpecial_program(String special_program) {
        this.special_program = special_program;
    }

    public PostItem(String item_sku, String from, String to, String customer_id, String special_program) {
        this.item_sku = item_sku;
        this.from = from;
        this.to = to;
        this.customer_id = customer_id;
        this.special_program = special_program;
    }

    public PostItem(String item_sku, String event_type, String from, String to, String customer_id, String special_program) {
        this.item_sku = item_sku;
        this.event_type = event_type;
        this.from = from;
        this.to = to;
        this.customer_id = customer_id;
        this.special_program = special_program;
    }
}
