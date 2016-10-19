package com.example.coraliemiquel.myapplication.Service;

/**
 * Created by Coralie MIQUEL on 19/10/2016.
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface  ConnexionService {

    //adress de notre serveur
        public static final String ENDPOINT = "http://etiennedesticourt.pythonanywhere.com/";


        @FormUrlEncoded
        @POST("add_command")
        Call<ResponseBody> setCommand(@Field("command") String command);

    }
