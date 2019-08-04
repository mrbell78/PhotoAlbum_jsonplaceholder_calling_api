package com.mrbell.photoalbum_retrofit2.api;

import com.mrbell.photoalbum_retrofit2.model.Album;
import com.mrbell.photoalbum_retrofit2.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("albums")
    Call<List<User>> getUser(@Query("userId") int userId);

    @GET("photos")
    Call<List<Album>> getAlbum(@Query("albumId") int albumId);
}
