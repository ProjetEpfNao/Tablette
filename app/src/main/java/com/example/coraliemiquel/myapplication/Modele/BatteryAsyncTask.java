package com.example.coraliemiquel.myapplication.Modele;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.coraliemiquel.myapplication.Events.BatteryEvent;
import com.example.coraliemiquel.myapplication.Manager.ConnectionManager;
import com.example.coraliemiquel.myapplication.R;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatteryAsyncTask extends AsyncTask {
    private static ConnectionManager myConnectionManager;

    public BatteryAsyncTask(ConnectionManager myConnectionManager) {
        this.myConnectionManager = myConnectionManager;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        myConnectionManager.getBatteryInfo();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}






