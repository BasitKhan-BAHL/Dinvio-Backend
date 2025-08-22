package com.basitk.dinvio.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "categories")
public class Category extends PanacheMongoEntity {

    @BsonProperty("name")
    public String name;

    @BsonProperty("description")
    public String description;

    @BsonProperty("icon")
    public String icon;

    @BsonProperty("restaurantCode")
    public String restaurantCode;

    @BsonProperty("userId")
    public String userId;

    public String getCategoryId() {
        return this.id != null ? this.id.toString() : null;
    }
}
