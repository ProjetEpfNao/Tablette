package com.example.coraliemiquel.myapplication.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.coraliemiquel.myapplication.R;

import retrofit2.Call;

/**
 * Created by Coralie MIQUEL on 16/11/2016.
 */

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //changement de vue
        Intent intent = new Intent(AdminActivity.this, ConnectActivity.class);
        startActivity(intent);
        return  super.onOptionsItemSelected(item);
    }
}
