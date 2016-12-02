package com.example.coraliemiquel.myapplication.Vue;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.coraliemiquel.myapplication.Events.BatteryEvent;
import com.example.coraliemiquel.myapplication.Manager.BatteryLevelManager;
import com.example.coraliemiquel.myapplication.Manager.ConnexionManager;
import com.example.coraliemiquel.myapplication.R;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public final static String STAND_UP = "stand_up";
    public ConnexionManager myConnexionManager = ConnexionManager.getInstance(this);
    public BatteryLevelManager myBatteryLevelManager = new BatteryLevelManager();
    private ImageView batteryView;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(BatteryEvent event){
        System.out.println("Received battery event.");
        if (this.batteryView == null) { return; }
        myBatteryLevelManager.updateBatteryView(event.battery, batteryView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //phoneNumber = (EditText) findViewById(R.id.NumberEditText01);
        // phoneNumber.setVisibility(View.INVISIBLE);


        VideoView vidView = (VideoView)findViewById(R.id.myVideo);

        //String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        // String vidAddress = "rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/definst/mp4:bigbuckbunnyiphone_400.mp4";
        String vidAddress = "http://54.152.73.101:8090/test.flv";
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.start();

        ImageView batteryView = (ImageView) findViewById (R.id.batteryLevel);
        this.batteryView = batteryView;
        myBatteryLevelManager.callAsynchronousTask(myConnexionManager,batteryView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Deconnection
        Call myCall;
        myCall = myConnexionManager.getLogout();
        checkResponse(myCall);
        //changement de vue
        Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
        startActivity(intent);
       return  super.onOptionsItemSelected(item);
    }


    public void onClick (View v){

        String value = null;
        View myView = findViewById(R.id.handUp);
        System.out.print("VALUE"+v.getId()+ " - "+R.string.handUp+"  "+myView.getId());
        switch(v.getId()){
            case R.string.batteryLevel: {
                value = getString(R.string.batteryLevel);
                break;
            }
            case R.string.handDown: {
                value = getString(R.string.headDown);
                break;
            }
            case R.string.happy: {
                value = getString(R.string.happy);
                break;
            }
            case R.string.headDown: {
                value = getString(R.string.headDown);
                break;
            }
            case R.string.headLeft: {
                value = getString(R.string.headLeft);
                break;
            }
            case R.string.headRight: {
                value = getString(R.string.headRight);
                break;
            }
            case R.string.headTop: {
                value = getString(R.string.headTop);
                break;
            }
            case R.string.sayThisMessage: {
                value = getString(R.string.sayThisMessage);
                break;
            }
            case R.string.sitDown: {
                value = getString(R.string.sitDown);
                break;
            }
            case R.string.speak: {
                value = getString(R.string.speak);
                break;
            }


            default : {
                value = getString(R.string.sitDown);
                break;
            }
        }





        Toast.makeText(getApplicationContext(), "OnButtonClicked : " + value, Toast.LENGTH_SHORT).show();

        if(value!=null){
            Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
            checkResponse(myCall);
        }
    }


    public void onClickHandUp(View v){
         String value = getString(R.string.handUp);
         Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
         checkResponse(myCall);
    }

    public void onClickStandUp(View v){
        String value = getString(R.string.standUp);
        Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
        checkResponse(myCall);
    }
    public void onClickSitDown(View v){
        String value = getString(R.string.sitDown);
        Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
        checkResponse(myCall);
    }
    public void onClickHeadUp(View v){
        String value = getString(R.string.headTop);
        Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
        checkResponse(myCall);
    }

    public void onClickHeadRight(View v){
        String value = getString(R.string.headRight);
        Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
        checkResponse(myCall);
    }

    public void onClickHeadLeft(View v){
        String value = getString(R.string.headLeft);
        Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
        checkResponse(myCall);
    }
    public void onClickHeadDown(View v){
        String value = getString(R.string.headDown);
        Call<ResponseBody> myCall = myConnexionManager.postCommand(value);
        checkResponse(myCall);
    }

    public void checkResponse(Call myCall){
        myCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println("SUCCESS.");
                try {
                                        String msg = response.body().string();
                    Toast.makeText(getApplicationContext(), "OnButtonClicked : " + msg, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "OnButtonClicked : Failure", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("FAILURE.");
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), "OnButtonClicked : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
