/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LNV
 */
public class Product_Size {
    private int productId;
    private int size;
    private int stock;

    // Constructor không tham số
    public Product_Size() {
    }

    // Constructor có tham số
    public Product_Size(int productId, int size, int stock) {
        this.productId = productId;
        this.size = size;
        this.stock = stock;
    }

    // Getter và Setter cho productId
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter và Setter cho size
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Getter và Setter cho stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Phương thức toString để hiển thị thông tin kích thước sản phẩm
    @Override
    public String toString() {
        return "ProductSize{" +
                "productId=" + productId +
                ", size=" + size +
                ", stock=" + stock +
                '}';
    }
}