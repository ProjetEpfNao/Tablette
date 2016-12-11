package com.example.coraliemiquel.myapplication.Manager;
import android.content.Context;

import com.example.coraliemiquel.myapplication.Events.AddCommandEvent;
import com.example.coraliemiquel.myapplication.Events.BatteryEvent;
import com.example.coraliemiquel.myapplication.Events.CallFailureEvent;
import com.example.coraliemiquel.myapplication.Events.LoginEvent;
import com.example.coraliemiquel.myapplication.Events.LogoutEvent;
import com.example.coraliemiquel.myapplication.Events.ServerResponseEvent;
import com.example.coraliemiquel.myapplication.Modele.ServerResponse;
import com.example.coraliemiquel.myapplication.Service.ConnectionService;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;


import org.greenrobot.eventbus.EventBus;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionManager {
    private ConnectionService connectionService;
    private static ConnectionManager ourInstance ;
    private static EventBus Bus = EventBus.getDefault();

    public static ConnectionManager getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new ConnectionManager(context);
        }
        return ourInstance;
    }

    private ConnectionManager(Context context) {
        CookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
                new SharedPrefsCookiePersistor(context));

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        connectionService = new Retrofit.Builder()
                .baseUrl(ConnectionService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ConnectionService.class);
    }

    private Callback<ServerResponse> generateCallback(final ServerResponseEvent event) {
        return (new Callback<ServerResponse>() {
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response){
                int code = response.code();
                ServerResponse serverResponse = response.body();
                event.setResponse(serverResponse);
                event.setCode(code);
                Bus.post(event);
            }

            public void onFailure(Call<ServerResponse> call, Throwable t){
                CallFailureEvent event = new CallFailureEvent(call);
                Bus.post(event);
            }
        });
    }

    public void login(String username, String password) {
        Call<ServerResponse> myCall = connectionService.login(username, password);
        Callback callback = generateCallback(new LoginEvent());
        myCall.enqueue(callback);
    }

    public void logout() {
        Call<ServerResponse> myCall = connectionService.logout();
        Callback callback = generateCallback(new LogoutEvent());
        myCall.enqueue(callback);
    }

    public void addCommand(String command) {
        Call<ServerResponse> myCall = connectionService.add_command(command);
        Callback callback = generateCallback(new AddCommandEvent());
        myCall.enqueue(callback);
    }

    public void getBatteryInfo() {
        Call<ServerResponse> myCall = connectionService.add_command("battery");
        Callback callback = generateCallback(new BatteryEvent());
        myCall.enqueue(callback);
    }


    public void creatNewUser(String username, String password,String ipNao){
        Call<ServerResponse> myCall = connectionService.register(username, password);
        Callback callback = generateCallback(new LoginEvent());
        myCall.enqueue(callback);
    }

    public void sayIt(String message){
        Call<ServerResponse> myCall = connectionService.register("speak", message);
        Callback callback = generateCallback(new LoginEvent());
        myCall.enqueue(callback);
    }
}