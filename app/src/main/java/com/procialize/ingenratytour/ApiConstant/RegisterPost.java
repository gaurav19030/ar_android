package com.procialize.ingenratytour.ApiConstant;

/**
 * Created by Naushad on 11/16/2017.
 */

public class RegisterPost {
    String first_name,last_name,mobile_no,location,user_device_type,user_device_id,user_device_token;

    public  RegisterPost(String first_name,String last_name,String mobile_no,String location,String user_device_type,String user_device_id,String user_device_token)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.mobile_no=mobile_no;
        this.location=location;
        this.user_device_type=user_device_type;
        this.user_device_id=user_device_id;
        this.user_device_token=user_device_token;
    }
}
