package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("link_name")
    @Expose
    private String linkName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

}