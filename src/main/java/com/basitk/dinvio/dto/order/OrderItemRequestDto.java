package com.basitk.dinvio.dto.order;

public class OrderItemRequestDto {
    public String menuItemId;
    public Integer quantity;

    public OrderItemRequestDto(String menuItemId, Integer quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemRequestDto{" +
                "menuItemId='" + menuItemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
