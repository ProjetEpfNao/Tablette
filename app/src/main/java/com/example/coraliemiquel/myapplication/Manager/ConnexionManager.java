package com.example.coraliemiquel.myapplication.Manager;

import android.content.Context;

import com.example.coraliemiquel.myapplication.Service.ConnexionService;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;


import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Coralie MIQUEL on 19/10/2016.
 */

public class ConnexionManager {
    private ConnexionService connexionService;

    private static ConnexionManager ourInstance ;

  /*  public static ConnexionManager getInstance() {
        return ourInstance;
    }*/

    public static ConnexionManager getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new ConnexionManager(context);
        }
        return ourInstance;
    }

    private ConnexionManager(Context context) {

        //creation de la boite à cookies
        CookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        connexionService = new Retrofit.Builder()
                .baseUrl(ConnexionService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ConnexionService.class);
    }

    public Call<ResponseBody> postCommand(String command){
        return connexionService.setCommand(command);
    }


     public Call<ResponseBody> setConnection(String userName, String password) {
        return connexionService.setConnection(userName, password);
    }

    public Call<ResponseBody> getLogout() {
        return connexionService.getLogout();
    }
    }
