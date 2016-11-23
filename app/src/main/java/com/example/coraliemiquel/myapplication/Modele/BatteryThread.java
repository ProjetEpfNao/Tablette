package com.example.coraliemiquel.myapplication.Modele;

import android.widget.ImageView;

import com.example.coraliemiquel.myapplication.Manager.ConnexionManager;
import com.example.coraliemiquel.myapplication.R;

/**
 * Created by Coralie MIQUEL on 16/11/2016.
 */

public class BatteryThread extends Thread {

    private static ConnexionManager myConnexionManager;
    private int batteryLevel = 1000000000;
    private ImageView myBatteryImageView;

    public BatteryThread(ConnexionManager myConnexionManager,ImageView myBatteryImageView){
        this.myConnexionManager = myConnexionManager;
        this.myBatteryImageView = myBatteryImageView;
    }
    @Override
    public void run(){
        while(myBatteryImageView != null) {
             // myConnexionManager.postCommand("battery");
             batteryLevel -= 1;
             if (batteryLevel > 7000) {
                myBatteryImageView.setImageResource(R.drawable.ic_full_battery);
             } else if (batteryLevel > 4000) {
                 myBatteryImageView.setImageResource(R.drawable.ic_middle_battery);
             } else if (batteryLevel > 30) {
                 myBatteryImageView.setImageResource(R.drawable.ic_low_battery);
             } else if (batteryLevel > 10) {
                 myBatteryImageView.setImageResource(R.drawable.ic_very_low_battery);
             } else {
                 myBatteryImageView.setImageResource(R.drawable.ic_arrow_right);
             }
        }
    }
}
