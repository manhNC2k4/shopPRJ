/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 *
 * @author LNV
 */


public class Product {
    private int id;
    private String name;
    private String description;
    private int categoryId;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;
    private List<String> images;
    private List<Product_Size> sizes;
    private int sale_id;
    // Constructor không tham số
    public Product() {
    }

    public Product(int id, String name, String description, int categoryId, BigDecimal price, Date createdAt, Date updatedAt, List<String> images, List<Product_Size> sizes, int sale_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
        this.sizes = sizes;
        this.sale_id = sale_id;
    }

    
    public Product(int id, String name, String description, int categoryId, BigDecimal price, Date createdAt, Date updatedAt, List<String> images, int sale_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
        this.sale_id = sale_id;
    }
    // Constructor có tham số
    public Product(int id, String name, String description, int categoryId, BigDecimal price, Date createdAt,  Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(int id, String name, String description, int categoryId, BigDecimal price, Date createdAt, Date updatedAt, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
    }

    public Product(int id, String name, String description, int categoryId, BigDecimal price, Date createdAt, Date updatedAt, List<String> images, List<Product_Size> sizes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
        this.sizes = sizes;
    }
    
    
    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter và Setter cho categoryId
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // Getter và Setter cho price
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Getter và Setter cho createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter và Setter cho updatedAt
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Product_Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Product_Size> sizes) {
        this.sizes = sizes;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }
    
    
    
    // Phương thức toString để hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
