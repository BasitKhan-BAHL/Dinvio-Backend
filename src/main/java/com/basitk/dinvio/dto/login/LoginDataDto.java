package com.basitk.dinvio.dto.login;

public class LoginDataDto {
    public String token;
    public String userId;
    public String username;
    public Integer roleId;
    public String roleName;
    public String restaurantCode;

    public LoginDataDto() {
    }

    public LoginDataDto(String token, String userId, String username, Integer roleId, String roleName, String restaurantCode) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.roleId = roleId;
        this.roleName = roleName;
        this.restaurantCode = restaurantCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }

    @Override
    public String toString() {
        return "LoginDataDto{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                '}';
    }
}


