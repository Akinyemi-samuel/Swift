package com.example.myapplication2;

public class history_Product {
    private String Rec_name;
    private String product;
    private String Order_id;

    public history_Product(String rec_name, String product, String order_id) {
        Rec_name = rec_name;
        this.product = product;
        Order_id = order_id;
    }

    public String getRec_name() {
        return Rec_name;
    }

    public String getProduct() {
        return product;
    }

    public String getOrder_id() {
        return Order_id;
    }
}
