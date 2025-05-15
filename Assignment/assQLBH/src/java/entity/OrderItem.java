/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author Quang Anh
 */
public class OrderItem {

    private int order_id, item_id;
    private String product_id;
    private int quantity;
    private BigDecimal list_price;
    private Product product;
    
    public OrderItem() {
    }

    public OrderItem(int order_id, int item_id, String product_id, int quantity, BigDecimal list_price) {
        this.order_id = order_id;
        this.item_id = item_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.list_price = list_price;
    }

    public OrderItem(int order_id, int item_id, int quantity, BigDecimal list_price,
            Product product) {
        this.order_id = order_id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.list_price = list_price;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getItem_id() {
        return item_id;
    }
    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }



    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price;
    }
    
    public BigDecimal getPriceAfter() {
        return list_price.multiply(new BigDecimal(quantity));
    }
    
    @Override
    public String toString() {
        return "OrderItem{" + "order_id=" + order_id + ", item_id=" + item_id + ", product_id=" + product_id + ", quantity=" + quantity + ", list_price=" + list_price + ", product=" + product + '}';
    }

}
