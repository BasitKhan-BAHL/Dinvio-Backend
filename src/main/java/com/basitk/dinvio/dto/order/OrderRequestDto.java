package com.basitk.dinvio.dto.order;

import java.util.List;

public class OrderRequestDto {
    public List<OrderItemRequestDto> items;

    public OrderRequestDto(List<OrderItemRequestDto> items) {
        this.items = items;
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
                "items=" + items +
                '}';
    }
}
