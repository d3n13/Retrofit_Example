package ru.bilenkod.retrofitexamplejava;

import com.google.gson.annotations.SerializedName;

public class Post {
    int id, userId;
    String body;
    @SerializedName("title")
    String name;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }
}
