package com.basitk.dinvio.dto.category;

import com.basitk.dinvio.model.Category;

public class CategoryDataDto {
    public String id;
    public String name;
    public String description;
    public String icon;
    public String restaurantCode;
    public String userId;

    public CategoryDataDto() {
    }

    public CategoryDataDto(String id, String name, String description, String icon, String restaurantCode, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.restaurantCode = restaurantCode;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CategoryDataDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
