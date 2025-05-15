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
public class Cart {
    private Vector<Item> items;

    public Cart() {
        items = new Vector<>();
    }

    public Cart(Vector<Item> items) {
        this.items = items;
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }
    
    private Item getiItemById(String id) {
        for(Item i:items) {
            if(i.getProduct().getProduct_id()== id)
                return i;
        }
        return null;
    }
    
    public int getQuantityByID(String id) {
        return getiItemById(id).getQuantity();
    }
    
    //Add cart
    public void addItem(Item t) {
        //Item already in cart
        if(getiItemById(t.getProduct().getProduct_id()) != null) {
            Item i = getiItemById(t.getProduct().getProduct_id());
            i.setQuantity(i.getQuantity()+t.getQuantity());
        } else {
            items.add(t);
        }
    }
    
    public void removeItem(String id) {
        if(getiItemById(id) != null) {
            items.remove(getiItemById(id));
        }
    }
    
    public double getTotalMoney() {
        double t=0;
        for(Item i:items) {
            t += i.getQuantity()*i.getPrice();
        }
        return t;
    }
}
