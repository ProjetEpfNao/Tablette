package com.example.coraliemiquel.myapplication.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.coraliemiquel.myapplication.R;

/**
 * Created by Coralie MIQUEL on 18/10/2016.
 */

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_activity);
    }


    public void onClickConnect(View v) {
        Intent intent = new Intent(ConnectActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
