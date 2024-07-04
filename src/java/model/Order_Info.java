/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LNV
 */
public class Order_Info {
    private int orderInfoId; // Thêm trường orderInfoId
    private String firstName;
    private String lastName;
    private String country;
    private String street;
    private String city;
    private String postcode;
    private String phone;
    private String email;
    private String payment;
    private int orderId;

    // Constructor, Getters và Setters

    public Order_Info() {
        // Constructor mặc định
    }

    // Constructor with parameters

    public Order_Info(int orderInfoId, String firstName, String lastName, String country, String street, String city, String postcode, String phone, String email, String payment, int orderId) {
        this.orderInfoId = orderInfoId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.phone = phone;
        this.email = email;
        this.payment = payment;
        this.orderId = orderId;
    }
    
    
    // Getters

    public int getOrderInfoId() {
        return orderInfoId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPayment() {
        return payment;
    }

    public int getOrderId() {
        return orderId;
    }


    // Setters

    public void setOrderInfoId(int orderInfoId) {
        this.orderInfoId = orderInfoId;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    //toString method
    @Override
    public String toString() {
        return "OrderInfo{" + "orderInfoId=" + orderInfoId + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country + ", street=" + street + ", city=" + city + ", postcode=" + postcode + ", phone=" + phone + ", email=" + email + ", payment=" + payment + ", orderId=" + orderId + '}';
    }

}
