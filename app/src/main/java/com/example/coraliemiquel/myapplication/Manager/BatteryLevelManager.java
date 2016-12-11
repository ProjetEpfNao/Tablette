package com.example.coraliemiquel.myapplication.Manager;

import android.os.Handler;
import android.widget.ImageView;

import com.example.coraliemiquel.myapplication.Events.BatteryEvent;
import com.example.coraliemiquel.myapplication.Modele.BatteryAsyncTask;
import com.example.coraliemiquel.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

public class BatteryLevelManager {

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

    public void callAsynchronousTask(ConnectionManager connectionManager) {
        final ConnectionManager myConnectionManager = connectionManager;
        Timer timer = new Timer();
        final Handler handler =  new Handler();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            BatteryAsyncTask myBatteryAsyncTask = new BatteryAsyncTask(myConnectionManager);
                            myBatteryAsyncTask.execute();
                        }
                        catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000); //execute in every 3s
    }
}
