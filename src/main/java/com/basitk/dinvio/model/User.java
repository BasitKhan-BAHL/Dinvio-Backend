package com.basitk.dinvio.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "users")
public class User extends PanacheMongoEntity {

    @BsonProperty("username")
    public String username;

    @BsonProperty("password")
    public String password;

    @BsonProperty("role")
    public String role;

    @BsonProperty("restaurant_code")
    public String restaurantCode;

    @BsonProperty("user_id")
    public Integer userId;

    @BsonProperty("role_id")
    public Integer roleId;
}
