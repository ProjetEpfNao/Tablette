package com.example.coraliemiquel.myapplication.Events;

import retrofit2.Call;

public class CallFailureEvent {
    public Call call;

    public CallFailureEvent(Call call) {
        this.call = call;
    }
}
