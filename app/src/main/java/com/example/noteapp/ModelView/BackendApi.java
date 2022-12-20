package com.example.noteapp.ModelView;

import com.example.noteapp.Model.Note;
import com.example.noteapp.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface BackendApi {

    @POST("user/register")
    Call<User> register(@Body User user);

    @POST("user/login")
    Call<User> login(@Body User user);

    @POST("note/add")
    Call<String> addNote(@Header("Authorization")String token,@Body Note note);

    @PUT("note/update")
    Call<String> updateNote(@Header("Authorization")String token,@Body Note note);

    @GET("note/get")
    Call<Note> getNote(@Header("Authorization")String token,String id);

    @GET("note/getAll")
    Call<List<Note>> getAllNote(@Header("Authorization")String token);

    @DELETE("note/delete")
    Call<String> deleteNote(@Header("Authorization")String token,@Query("id") String id);

    @DELETE("note/deleteAll")
    Call<String> deleteAllNote(@Header("Authorization")String token);

}
