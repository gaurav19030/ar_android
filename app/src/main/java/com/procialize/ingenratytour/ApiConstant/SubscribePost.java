package com.procialize.ingenratytour.ApiConstant;

/**
 * Created by Naushad on 11/16/2017.
 */

public class SubscribePost {
    String user_name,mail_id,phone_number,product_id,access_token;

    public  SubscribePost(String user_name,String mail_id,String phone_number,String product_id,String access_token)
    {
        this.user_name=user_name;
        this.mail_id=mail_id;
        this.phone_number=phone_number;
        this.product_id=product_id;
        this.access_token=access_token;
    }

}
