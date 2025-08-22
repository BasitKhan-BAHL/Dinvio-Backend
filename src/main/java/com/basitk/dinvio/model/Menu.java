package com.basitk.dinvio.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "menu")
public class Menu extends PanacheMongoEntity {

    @BsonProperty("name")
    public String name;

    @BsonProperty("description")
    public String description;

    @BsonProperty("price")
    public Double price;

    @BsonProperty("categoryId")
    public String categoryId;

    @BsonProperty("restaurantCode")
    public String restaurantCode;

    @BsonProperty("userId")
    public String userId;

    public String getMenuId() {
        return this.id != null ? this.id.toString() : null;
    }
}
