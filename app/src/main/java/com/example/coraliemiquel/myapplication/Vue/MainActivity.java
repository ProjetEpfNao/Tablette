package com.example.coraliemiquel.myapplication.Vue;


import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.coraliemiquel.myapplication.Events.AddCommandEvent;
import com.example.coraliemiquel.myapplication.Events.BatteryEvent;
import com.example.coraliemiquel.myapplication.Events.CallFailureEvent;
import com.example.coraliemiquel.myapplication.Events.LogoutEvent;
import com.example.coraliemiquel.myapplication.Manager.BatteryLevelManager;
import com.example.coraliemiquel.myapplication.Manager.ConnectionManager;
import com.example.coraliemiquel.myapplication.R;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    public ConnectionManager myConnectionManager = ConnectionManager.getInstance(this);
    public BatteryLevelManager myBatteryLevelManager = new BatteryLevelManager();
    private ImageView batteryView;
    private EventBus bus = EventBus.getDefault();

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @Subscribe
    public void onAddCommandEvent(AddCommandEvent event) {
        Log.d("EVENT", "Received AddCommand event.");
        //TODO: DEAL WITH ERRORS HERE: SHOW MESSAGE TO USER
    }

    @Subscribe
    public void onBatteryEvent(BatteryEvent event) {
        Log.d("EVENT", "Received battery event.");
        if (this.batteryView == null) { return; }
        myBatteryLevelManager.updateBatteryView(event.battery, batteryView);
    }

    @Subscribe
    public void onLogoutEvent(LogoutEvent event) {
        Log.d("EVENT", "Received logout event.");
        if (event.code == 200) {
            if (event.response.getResult().equals("SUCCESS")) {
                Log.d("SERVER RESPONSE", "Logged out.");
                Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
                startActivity(intent);
            }
            else {
                Log.d("SERVER RESPONSE", "Failure status. Failed to logout.");
                //TODO: DEAL WITH ERROR: SHOW ERROR; DONT GO TO NEXT ACTIVITY
            }
        }
        else {
            Log.d("SERVER RESPONSE", "Non 200 code. Failed to logout.");
            //TODO: DEAL WITH ERROR: SHOW ERROR; DONT GO TO NEXT ACTIVITY
        }
    }

    @Subscribe
    public void onCallFailureEvent(CallFailureEvent event) {
        Log.d("EVENT", "Received CallFailure event.");
        //TODO: DEAL WITH ERROR: SHOW MESSAGE TO USER ABOUT CONNECTION ERROR
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.myVideo);
        String vidAddress = "http://54.152.73.101:8090/test.webm";
        Uri vidUri = Uri.parse(vidAddress);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(vidAddress);

        this.batteryView = (ImageView) findViewById (R.id.batteryLevel);
        myBatteryLevelManager.callAsynchronousTask(myConnectionManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        myConnectionManager.logout();
        return  super.onOptionsItemSelected(item);
    }

    private void executeCommand(String command) {
        myConnectionManager.addCommand(command);
    }

    public void onClickHandUp(View v){
         String command = getString(R.string.handUp);
         executeCommand(command);
    }

    public void onClickStandUp(View v){
        String command = getString(R.string.standUp);
        executeCommand(command);
    }

    public void onClickSitDown(View v){
        String command = getString(R.string.sitDown);
        executeCommand(command);
    }

    public void onClickHeadUp(View v){
        String command = getString(R.string.headTop);
        executeCommand(command);
    }

    public void onClickHeadRight(View v){
        String command = getString(R.string.headRight);
        executeCommand(command);
    }

    public void onClickHeadLeft(View v){
        String command = getString(R.string.headLeft);
        executeCommand(command);
    }

    public void onClickHeadDown(View v){
        String command = getString(R.string.headDown);
        executeCommand(command);
    }
}
