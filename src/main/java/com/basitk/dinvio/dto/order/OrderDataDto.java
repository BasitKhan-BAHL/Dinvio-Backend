package com.basitk.dinvio.dto.order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderDataDto {
    public String id;
    public String restaurantCode;
    public String userId;
    public List<OrderItemDataDto> items;
    public Double totalPrice;
    public String status;
    public LocalDate date;
    public LocalTime time;

    public OrderDataDto() {
    }

    public OrderDataDto(String id, String restaurantCode, String userId, List<OrderItemDataDto> items, Double totalPrice, String status, LocalDate date, LocalTime time) {
        this.id = id;
        this.restaurantCode = restaurantCode;
        this.userId = userId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<OrderItemDataDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDataDto> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OrderDataDto{" +
                "id='" + id + '\'' +
                ", restaurantCode='" + restaurantCode + '\'' +
                ", userId='" + userId + '\'' +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
