/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LNV
 */
public class ProductInfo {
    private Product product;
    private Product_Size size;
    private int quantity;
    private int cart_item_id;

    public int getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(int cart_item_id) {
        this.cart_item_id = cart_item_id;
    }
    
    
    public ProductInfo(Product product, Product_Size size, int quantity) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
    }

    public ProductInfo(Product product, Product_Size size, int quantity, int cart_item_id) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.cart_item_id = cart_item_id;
    }

    // Getters và setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product_Size getSize() {
        return size;
    }

    public void setSize(Product_Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
