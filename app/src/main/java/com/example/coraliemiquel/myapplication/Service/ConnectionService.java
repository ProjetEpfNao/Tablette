package com.example.coraliemiquel.myapplication.Service;

import com.example.coraliemiquel.myapplication.Modele.ServerResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConnectionService {
    String ENDPOINT = "http://etiennedesticourt.pythonanywhere.com/";

    @GET("logout")
    Call<ServerResponse> logout();

    @FormUrlEncoded
    @POST("add_command")
    Call<ServerResponse> add_command(@Field("command") String command);

    @FormUrlEncoded
    @POST("login")
    Call<ServerResponse> login(@Field("username") String username, @Field("password") String password );
}
