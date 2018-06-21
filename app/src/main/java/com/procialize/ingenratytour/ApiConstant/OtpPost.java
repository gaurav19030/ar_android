package com.procialize.ingenratytour.ApiConstant;

/**
 * Created by Naushad on 11/16/2017.
 */

public class OtpPost {

    String otp,user_id,access_token;

    public  OtpPost(String otp,String user_id,String access_token)
    {
        this.otp=otp;
        this.user_id=user_id;
        this.access_token=access_token;
    }
}
