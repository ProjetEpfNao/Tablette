package com.example.coraliemiquel.myapplication.Service;

/**
 * Created by Coralie MIQUEL on 19/10/2016.
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface  ConnexionService {

    //adress de notre serveur
        public static final String ENDPOINT = "http://etiennedesticourt.pythonanywhere.com/";


        @GET("logout")
        Call<ResponseBody> getLogout();

        @FormUrlEncoded
        @POST("add_command")
        Call<ResponseBody> setCommand(@Field("command") String command);

        @FormUrlEncoded
        @POST("login")
        Call<ResponseBody> setConnection(@Field("username") String username,@Field("password") String password );


    }
