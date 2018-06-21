package com.procialize.ingenratytour.ApiConstant;

/**
 * Created by Naushad on 10/30/2017.
 */

import com.procialize.ingenratytour.GetterSetter.AboutTour;
import com.procialize.ingenratytour.GetterSetter.DrawerGallery;
import com.procialize.ingenratytour.GetterSetter.MapGetter;
import com.procialize.ingenratytour.GetterSetter.MobileLogin;
import com.procialize.ingenratytour.GetterSetter.OtpData;
import com.procialize.ingenratytour.GetterSetter.ProductImages;
import com.procialize.ingenratytour.GetterSetter.ProductData;
import com.procialize.ingenratytour.GetterSetter.QuizData;
import com.procialize.ingenratytour.GetterSetter.RegisterData;
import com.procialize.ingenratytour.GetterSetter.ShareExperience;
import com.procialize.ingenratytour.GetterSetter.ShareExperienceListPost;
import com.procialize.ingenratytour.GetterSetter.ShareExperiencePost;
import com.procialize.ingenratytour.GetterSetter.Subscribe;
import com.procialize.ingenratytour.GetterSetter.ViewCount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {


    @POST("products")
    Call<ProductData>ProductGet(@Body ProductsPost data);

    @POST("gallary")
    Call<DrawerGallery> DrawerGalleryGet(@Body ProductsPost data);

    @POST("about_ingenuity_tour")
    Call<AboutTour> IntegrityTourGet(@Body ProductsPost data);

    @POST("product_image")
    Call<ProductImages> ProductGalleryGet( @Body ProductImagepost data);

    @POST("register")
    Call<RegisterData> Register(@Body RegisterPost data);

    @POST("loginmobile")
    Call<MobileLogin> MobileLogin(@Body LoginPost data);

    @POST("login")
    Call<OtpData> Otp(@Body OtpPost data);

    @POST("product_video")
    Call<ProductImages> ProductVideoGet( @Body ProductImagepost data);

    @POST("product_brochure")
    Call<ProductImages> ProductBroucherGet( @Body ProductImagepost data);


    @POST("product_image_view")
    Call<ViewCount> ProductImageViewPost(@Body ProductViewpost data);

    @POST("product_video_view")
    Call<ViewCount> ProductVideoViewpost( @Body ProductViewpost data);

    @POST("product_brochure_view")
    Call<ViewCount> ProductBroucherViewPost( @Body ProductViewpost data);

    @POST("subscribe")
    Call<Subscribe> SubscribePost(@Body SubscribePost data);

    @POST("quiz_reply")
    Call<ShareExperience> ShareExperiencePost(@Body ShareExperiencePost data);

    @POST("quiz_list")
    Call<QuizData> ShareExperienceListPost(@Body ShareExperienceListPost data);

    @GET("link")
    Call<MapGetter> getMap();

}
