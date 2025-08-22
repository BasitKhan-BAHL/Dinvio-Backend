package com.basitk.dinvio.dto.register;

public class RegisterRequestDto {
    public String username;
    public String password;
    public String role;
    public String restaurantCode;
    public Integer userId;
    public Integer roleId;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String username, String password, String role, String restaurantCode, Integer userId, Integer roleId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.restaurantCode = restaurantCode;
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RegisterRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
