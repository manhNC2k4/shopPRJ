package DTO;

import java.util.Date;

/**
 *
 * @author LNV
 */
public class UserDTO {

    private int user_id;
    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private String phone;
    private String address;
    private Date created_at;
    private Date updated_at;
    private int role;

    // Constructor nhận chuỗi
    public UserDTO(String serializedData) {
        String[] parts = serializedData.split("-");
        this.user_id = Integer.parseInt(parts[0]);
        this.username = parts[1];
        this.email = parts[2];
        this.first_name = parts[3];
        this.last_name = parts[4];
        this.phone = parts[5];
        this.address = parts[6];
        // Chuyển đổi từ mili giây thành Date
        this.created_at = new Date(Long.parseLong(parts[7]));
        this.updated_at = new Date(Long.parseLong(parts[8]));
        this.role = Integer.parseInt(parts[9]);
    }

    // Constructor không tham số
    public UserDTO() {
    }

    // Constructor có tham số
    public UserDTO(int user_id, String username, String email, String first_name, String last_name, String phone, String address, Date created_at, Date updated_at, int role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.role = role;
    }

    // Getter và Setter cho user_id
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    // Getter và Setter cho username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho first_name
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    // Getter và Setter cho last_name
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    // Getter và Setter cho phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter và Setter cho address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getRole() {
        return role;
    }

    // Phương thức toString để hiển thị thông tin người dùng
    @Override
    public String toString() {
        return user_id + "-" + username + "-" + email + "-" + first_name + "-" + last_name + "-" + phone + "-" + address + "-" + created_at.getTime() + "-" + updated_at.getTime() + "-" + role;
    }
}
