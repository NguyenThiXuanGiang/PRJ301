/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Quang Anh
 */
public class Product {
    private String product_id;
    private String name;
    private int quantity;
    private float price;
    private String describe;
    private int cid;
    private String image;
    private Category category;

    public Product(String product_id, String name, int quantity, float price) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String product_id, String name, int quantity, float price, String describe,
            int cid, String image) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.describe = describe;
        this.cid = cid;
        this.image = image;
    }

    
    public Product() {
    }

    public Product(String product_id, String name, int quantity, float price,
            String describe, String image, Category category) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.describe = describe;
        this.image = image;
        this.category = category;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String getProductPrice() {
        String formattedPrice = String.format("%,.0f", price);
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }
    
    //dung hien thi cho cart
    public double getAfterPrice() {
      return price*quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", describe=" + describe + ", cid=" + cid + ", image=" + image + ", category=" + category + '}';
    }
    
}
