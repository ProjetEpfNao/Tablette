package com.example.coraliemiquel.myapplication.Manager;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.coraliemiquel.myapplication.Modele.BatteryAsyncTask;
import com.example.coraliemiquel.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class BatteryLevelManager {

    public BatteryLevelManager() {
    }

    public void updateBatteryView(int batteryLevel, ImageView myBatteryImageView) {
        if (batteryLevel > 70) {
            myBatteryImageView.setImageResource(R.drawable.ic_full_battery);
        } else if (batteryLevel > 40) {
            myBatteryImageView.setImageResource(R.drawable.ic_middle_battery);
        } else if (batteryLevel > 30) {
            myBatteryImageView.setImageResource(R.drawable.ic_low_battery);
        } else if (batteryLevel > 10) {
            myBatteryImageView.setImageResource(R.drawable.ic_very_low_battery);
        } else {
            myBatteryImageView.setImageResource(R.drawable.ic_arrow_right);
        }
    }

    public void callAsynchronousTask(ConnexionManager connexionManager, ImageView batteryImageView) {
        final ImageView myBatteryImageView = batteryImageView;
        final ConnexionManager myConnexionManager = connexionManager;
        final int test = 100;
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                            BatteryAsyncTask myBatteryAsyncTask = new BatteryAsyncTask(myConnexionManager,myBatteryImageView);
                            // PerformBackgroundTask this class is the class that extends AsynchTask
                            myBatteryAsyncTask.execute();

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 3000); //execute in every 30s
    }
}
