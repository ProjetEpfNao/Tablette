package com.example.coraliemiquel.myapplication.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.coraliemiquel.myapplication.Manager.ConnexionManager;
import com.example.coraliemiquel.myapplication.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        EditText loginEditText = (EditText) findViewById(R.id.login);
        EditText passwordEditText = (EditText) findViewById(R.id.password);

        String loginString = loginEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();


       if(loginString.equalsIgnoreCase("admin") && passwordString.equalsIgnoreCase("admin")){

           Intent intent = new Intent(ConnectActivity.this, AdminActivity.class);
           startActivity(intent);

       }else{
           ConnexionManager myConnexionManager = ConnexionManager.getInstance(this);
           Call<ResponseBody> myCall = myConnexionManager.setConnection(loginString,passwordString);

           checkResponse(myCall);
           Intent intent = new Intent(ConnectActivity.this, MainActivity.class);
           startActivity(intent);
       }

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
