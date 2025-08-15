package com.basitk.dinvio.dto.order;

import java.util.List;

public class OrderRequestDto {
    public String restaurantCode;
    public List<OrderItemRequestDto> items;

    public OrderRequestDto(String restaurantCode, List<OrderItemRequestDto> items) {
        this.restaurantCode = restaurantCode;
        this.items = items;
    }

    public String getRestaurantCode() {
        return restaurantCode;
    }

    public void setRestaurantCode(String restaurantCode) {
        this.restaurantCode = restaurantCode;
    }

    public List<OrderItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDto> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderRequestDto{" +
                "restaurantCode='" + restaurantCode + '\'' +
                ", items=" + items +
                '}';
    }
}
