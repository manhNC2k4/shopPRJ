/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LNV
 */
public class Cart {
    private int id;
    private int user_id;
    private int nums_items;

    public Cart() {
    }

    public Cart(int id, int user_id, int nums_items) {
        this.id = id;
        this.user_id = user_id;
        this.nums_items = nums_items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNums_items() {
        return nums_items;
    }

    public void setNums_items(int nums_items) {
        this.nums_items = nums_items;
    }
    
    
}
