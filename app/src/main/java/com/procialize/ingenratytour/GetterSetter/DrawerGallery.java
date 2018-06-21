package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Naushad on 11/4/2017.
 */

public class DrawerGallery {

    @SerializedName("Data")
    @Expose
    private List<GalleryData> data = null;
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Message")
    @Expose
    private String message;

    public List<GalleryData> getData() {
        return data;
    }

    public void setData(List<GalleryData> data) {
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

