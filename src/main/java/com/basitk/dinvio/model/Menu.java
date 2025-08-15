package com.basitk.dinvio.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "menu")
public class Menu extends PanacheMongoEntity {

    @BsonProperty("category")
    public String category;

    @BsonProperty("name")
    public String name;

    @BsonProperty("description")
    public String description;

    @BsonProperty("price")
    public Double price;
}
