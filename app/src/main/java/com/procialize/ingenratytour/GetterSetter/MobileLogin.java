package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naushad on 11/28/2017.
 */

public class MobileLogin {

    @SerializedName("Data")
    @Expose
    private MobileLoginData data;
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Message")
    @Expose
    private String message;

    public MobileLoginData getData() {
        return data;
    }

    public void setData(MobileLoginData data) {
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