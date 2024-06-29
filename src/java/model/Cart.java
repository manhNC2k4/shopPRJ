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

    // Constructor chuyển chuỗi thành đối tượng Cart
    public Cart(String cartString) throws IllegalArgumentException {
        String[] parts = cartString.split("\\|");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid cart string format");
        }
        try {
            this.id = Integer.parseInt(parts[0]);
            this.user_id = Integer.parseInt(parts[1]);
            this.nums_items = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in cart string", e);
        }
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

    @Override
    public String toString() {
        return id + "|" + user_id + "|" + nums_items;
    }
    
    
}
