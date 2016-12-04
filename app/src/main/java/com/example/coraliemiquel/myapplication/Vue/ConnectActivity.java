package com.example.coraliemiquel.myapplication.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.coraliemiquel.myapplication.Events.CallFailureEvent;
import com.example.coraliemiquel.myapplication.Events.LoginEvent;
import com.example.coraliemiquel.myapplication.Manager.ConnectionManager;
import com.example.coraliemiquel.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ConnectActivity extends AppCompatActivity {
    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_activity);
    }

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

    public void onClickConnect(View v) {
        EditText loginEditText = (EditText) findViewById(R.id.login);
        EditText passwordEditText = (EditText) findViewById(R.id.password);

        String loginString = loginEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();


        if(loginString.equalsIgnoreCase("admin") && passwordString.equalsIgnoreCase("admin")) {
            Intent intent = new Intent(ConnectActivity.this, AdminActivity.class);
            startActivity(intent);
        }
        else {
            ConnectionManager connectionManager = ConnectionManager.getInstance(this);
            connectionManager.login(loginString, passwordString);
        }
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
        Log.d("EVENT", "Received login event.");
        if (event.code == 200) {
            if (event.response.getResult().equals("SUCCESS")) {
                Log.d("SERVER RESPONSE", "Successful login.");
                Intent intent = new Intent(ConnectActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Log.d("SERVER RESPONSE", "Failure status. Failed to login.");
                //TODO: DEAL WITH ERROR: SHOW ERROR; DONT GO TO NEXT ACTIVITY
            }
        }
        else {
            Log.d("SERVER RESPONSE", "Non 200 code. Failed to login.");
            //TODO: DEAL WITH ERROR: SHOW ERROR; DONT GO TO NEXT ACTIVITY
        }
    }

    @Subscribe
    public void onCallFailureEvent(CallFailureEvent event) {
        Log.d("EVENT", "Received CallFailure event.");
        //TODO: DEAL WITH ERROR: SHOW MESSAGE TO USER ABOUT CONNECTION ERROR
    }
}
