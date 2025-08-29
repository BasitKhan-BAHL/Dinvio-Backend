package com.basitk.dinvio.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@MongoEntity(collection = "orders")
public class Order extends PanacheMongoEntity {

    @BsonProperty("restaurantCode")
    public String restaurantCode;

    @BsonProperty("userId")
    public String userId;

    @BsonProperty("items")
    public List<OrderItem> items;

    @BsonProperty("totalPrice")
    public Double totalPrice;

    @BsonProperty("status")
    public String status;

    @BsonProperty("date")
    public LocalDate date;

    @BsonProperty("time")
    public LocalTime time;
}
