package com.basitk.dinvio.dto.login;

public class LoginDataDto {
    public String token;
    public String username;
    public String role;
    public String restaurantCode;
    public Integer userId;
    public Integer roleId;

    public LoginDataDto(String token, String username, String role, String restaurantCode, Integer userId, Integer roleId) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.restaurantCode = restaurantCode;
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "LoginDataDto{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}


