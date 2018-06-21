package com.procialize.ingenratytour.ApiConstant;


import com.procialize.ingenratytour.RetrofitClient;

/**
 * Created by Naushad on 10/30/2017.
 */

public class ApiUtils {

    private ApiUtils() {}


    public static APIService getAPIService() {

        return RetrofitClient.getClient(ApiConstant.baseUrl).create(APIService.class);
    }
}
