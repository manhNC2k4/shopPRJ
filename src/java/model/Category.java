/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Category {

    private int category_id;
    private String name;
    private String text;
    private Date created_at;
    private Date updated_at;

    // Constructor không tham số
    public Category() {
    }

    // Constructor có tham số
    public Category(int category_id, String name, String text, Date created_at, Date updated_at) {
        this.category_id = category_id;
        this.name = name;
        this.text = text;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getter và Setter cho category_id
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter và Setter cho created_at
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    // Getter và Setter cho updated_at
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    // Phương thức toString để hiển thị thông tin danh mục
    @Override
    public String toString() {
        return "Category{"
                + "category_id=" + category_id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", created_at=" + created_at
                + ", updated_at=" + updated_at
                + '}';
    }
}
