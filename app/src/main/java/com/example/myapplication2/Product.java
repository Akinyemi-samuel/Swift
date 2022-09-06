package com.example.myapplication2;

public class Product {
    private String Order_id;
    private String Receipient_name;
    private String Receipient_Phone;
    private String Reciepient_Address;
    private String Order_details;

    public Product(String order_id, String receipient_name, String receipient_Phone, String reciepient_Address, String order_details) {
        Order_id = order_id;
        Receipient_name = receipient_name;
        Receipient_Phone = receipient_Phone;
        Reciepient_Address = reciepient_Address;
        Order_details = order_details;
    }

    public String getOrder_id() {
        return Order_id;
    }

    public String getReceipient_name() {
        return Receipient_name;
    }

    public String getReceipient_Phone() {
        return Receipient_Phone;
    }

    public String getReciepient_Address() {
        return Reciepient_Address;
    }

    public String getOrder_details() {
        return Order_details;
    }
}