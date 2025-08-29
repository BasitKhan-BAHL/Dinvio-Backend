package com.basitk.dinvio.dto.order;

public class OrderItemDataDto {
    public String menuItemId;
    public String categoryId;
    public String name;
    public Integer quantity;
    public Double price;
    public Double subtotal;

    public OrderItemDataDto() {
    }

    public OrderItemDataDto(String menuItemId, String categoryId, String name, Integer quantity, Double price, Double subtotal) {
        this.menuItemId = menuItemId;
        this.categoryId = categoryId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderItemDataDto{" +
                "menuItemId='" + menuItemId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subtotal=" + subtotal +
                '}';
    }
}
