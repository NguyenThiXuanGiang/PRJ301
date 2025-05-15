/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Vector;

/**
 *
 * @author Quang Anh
 */
public class Order {

    private int order_id;
    private String customer_id;
    private String order_status;
    private String order_date;
    private double totalMoney;
    private Vector<OrderItem> listOrderItems;

    public Order() {
    }

    public Order(int order_id, String customer_id, String order_status, String order_date, double totalMoney) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_status = order_status;
        this.order_date = order_date;
        this.totalMoney = totalMoney;
    }
    public Order(int order_id, String customer_id, String order_status, String order_date,
            double totalMoney, Vector<OrderItem> listOrderItems) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_status = order_status;
        this.order_date = order_date;
        this.totalMoney = totalMoney;
        this.listOrderItems = listOrderItems;
    }
    public Vector<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(Vector<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

}
