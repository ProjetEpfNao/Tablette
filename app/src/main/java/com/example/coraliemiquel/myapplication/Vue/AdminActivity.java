package com.example.coraliemiquel.myapplication.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Coralie MIQUEL on 03/11/2016.
 */

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(AdminActivity.this, ConnectActivity.class);
        startActivity(intent);
        return  super.onOptionsItemSelected(item);
    }
}
