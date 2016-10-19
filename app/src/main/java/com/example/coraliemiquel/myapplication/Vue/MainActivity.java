package com.example.coraliemiquel.myapplication.Vue;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.coraliemiquel.myapplication.Manager.ConnexionManager;
import com.example.coraliemiquel.myapplication.R;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public final static String STAND_UP = "stand_up";
    public ConnexionManager myConnexionManager = ConnexionManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickStandUp(View v) {
        Call<ResponseBody> myCall = myConnexionManager.postCommand(STAND_UP);
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
