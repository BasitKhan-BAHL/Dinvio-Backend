package com.basitk.dinvio.dto.menu;

public class MenuItemRequestDto {
    public String name;
    public String description;
    public Double price;
    public String categoryId;

    public MenuItemRequestDto() {
    }

    public MenuItemRequestDto(String name, String description, Double price, String categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "MenuItemRequestDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
