package com.example.coraliemiquel.myapplication.Events;

import com.example.coraliemiquel.myapplication.Modele.ServerResponse;

public class BatteryEvent extends ServerResponseEvent {
    public int battery = 0;

    public void setResponse(ServerResponse response) {
        super.setResponse(response);

        if (response.getContent() != null) {
            battery = Integer.valueOf(response.getContent());
        }
    }
}
