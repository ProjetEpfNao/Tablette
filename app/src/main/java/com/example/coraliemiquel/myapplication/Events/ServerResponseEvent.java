package com.example.coraliemiquel.myapplication.Events;

import com.example.coraliemiquel.myapplication.Modele.ServerResponse;

public class ServerResponseEvent {
    public ServerResponse response;
    public int code;

    public void setResponse(ServerResponse response) {
        this.response = response;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
