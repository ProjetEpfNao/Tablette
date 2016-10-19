package com.example.coraliemiquel.myapplication.Manager;

import com.example.coraliemiquel.myapplication.Service.ConnexionService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Coralie MIQUEL on 19/10/2016.
 */

public class ConnexionManager {
    private ConnexionService connexionService;

    private static ConnexionManager ourInstance = new ConnexionManager();

    public static ConnexionManager getInstance() {
        return ourInstance;
    }

    private ConnexionManager() {

        connexionService = new Retrofit.Builder()
                .baseUrl(ConnexionService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ConnexionService.class);
    }

    public Call<ResponseBody> postCommand(String command){
        return connexionService.setCommand(command);
    }


}
