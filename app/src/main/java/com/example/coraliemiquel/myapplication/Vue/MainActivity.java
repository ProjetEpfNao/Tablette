package com.example.coraliemiquel.myapplication.Vue;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
        startActivity(intent);
       return  super.onOptionsItemSelected(item);
    }

    public void onClickStandUp(View v) {
        Call<ResponseBody> myCall = myConnexionManager.postCommand(STAND_UP);
        checkResponse(myCall);
    }

    public void onClick (View v){

        String value = null;
        switch(v.getId()){
            case R.string.batteryLevel: {
                value = getString(R.string.batteryLevel);
                break;
            }
            case R.string.handDown: {
                 value = getString(R.string.headDown);
                break;
            }
            case R.string.handUp: {
                value = getString(R.string.handUp);
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
            case R.string.standUp: {
                value = getString(R.string.standUp);
                break;
            }
            case R.string.unhappy: {
                value = getString(R.string.unhappy);
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
