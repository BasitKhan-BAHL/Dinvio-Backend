package com.basitk.dinvio.dto.menu;

public class MenuItemDataDto {
    public String id;
    public String name;
    public String description;
    public Double price;
    public String categoryId;
    public String restaurantCode;
    public String userId;

    public MenuItemDataDto() {
    }

    public MenuItemDataDto(String id, String name, String description, Double price, String categoryId, String restaurantCode, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
        return "MenuItemDataDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
