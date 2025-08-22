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

    @BsonProperty("roleId")
    public Integer roleId;

    @BsonProperty("restaurantCode")
    public String restaurantCode;

    public String getUserId() {
        return this.id != null ? this.id.toString() : null;
    }
}
