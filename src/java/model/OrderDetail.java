    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author LNV
 */
public class OrderDetail {
    private int id;
    private int orderId;
    private int quantity;
    private BigDecimal price;
    private int productSizeId;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int quantity, BigDecimal price, int productSizeId) {
        this.id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.productSizeId = productSizeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(int productSizeId) {
        this.productSizeId = productSizeId;
    }
   
}
