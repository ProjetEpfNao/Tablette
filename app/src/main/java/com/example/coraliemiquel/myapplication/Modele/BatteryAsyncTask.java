package com.example.coraliemiquel.myapplication.Modele;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.coraliemiquel.myapplication.Events.BatteryEvent;
import com.example.coraliemiquel.myapplication.Manager.ConnexionManager;
import com.example.coraliemiquel.myapplication.R;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatteryAsyncTask extends AsyncTask {

    private static ConnexionManager myConnexionManager;
    static int batteryLevel = 100;
    private ImageView myBatteryImageView;

    public BatteryAsyncTask(ConnexionManager myConnexionManager, ImageView myBatteryImageView) {
        this.myConnexionManager = myConnexionManager;
        this.myBatteryImageView = myBatteryImageView;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Call<ResponseBody> response = myConnexionManager.postCommand("battery");
        getStringBatterie(response);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }


    private void getStringBatterie(Call myCall) {
        Callback myCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("OnResponse called.");
                System.out.println(response.code());
                System.out.println(response.message());

                if (response.code() == 200) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        try {
                            String message = body.string();
                            System.out.println(message);
                            //TODO: Create battery object, parse json with gson
                            //String battery = message.split(": ")[1];
                            //battery = battery.split(",")[0];
                            //int batteryLevel = Float.valueOf(battery).intValue();
                            int batteryLevel = 90;
                            EventBus.getDefault().post(new BatteryEvent(batteryLevel));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    ResponseBody body = response.body();
                    if (body != null) {
                        String message = body.string();
                        if (message != null) {
                            System.out.println(message);
                        } else {
                            System.out.println("Null response.");
                        }
                    }
                    else {

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("OnFailure called.");
                System.out.println(t.getMessage());
            }
        };
        myCall.enqueue(myCallback);
    }
}






