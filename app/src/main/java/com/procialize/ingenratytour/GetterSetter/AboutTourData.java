package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutTourData {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_name")
    @Expose
    private String imageName;
    @SerializedName("image_path")
    @Expose
    private String imagePath;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
