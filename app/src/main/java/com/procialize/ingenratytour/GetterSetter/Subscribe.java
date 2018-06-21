package com.procialize.ingenratytour.GetterSetter;

/**
 * Created by Naushad on 11/16/2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscribe {

    @SerializedName("Data")
    @Expose
    private SubscrtibeData data;
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Message")
    @Expose
    private String message;

    public SubscrtibeData getData() {
        return data;
    }

    public void setData(SubscrtibeData data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}