package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutTour {

    @SerializedName("Data")
    @Expose
    private AboutTourData data;
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Message")
    @Expose
    private String message;

    public AboutTourData getData() {
        return data;
    }

    public void setData(AboutTourData data) {
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