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

    @BsonProperty("color1")
    public String color1;

    @BsonProperty("color2")
    public String color2;
}
