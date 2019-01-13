package ru.bilenkod.retrofitexamplejava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonInfoApi {
    @GET("posts")
    Call<List<Post>> getPosts();
}
