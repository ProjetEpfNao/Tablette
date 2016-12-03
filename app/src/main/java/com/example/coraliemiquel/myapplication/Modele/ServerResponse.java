package com.example.coraliemiquel.myapplication.Modele;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("result")
    private String result;
    @SerializedName("error_message")
    private String errorMessage;
    @SerializedName("content")
    private String content;

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getContent() {
        return content;
    }
}
