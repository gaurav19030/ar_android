package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naushad on 11/16/2017.
 */

public class SubscrtibeData {

    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("subscribe_date")
    @Expose
    private String subscribeDate;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
