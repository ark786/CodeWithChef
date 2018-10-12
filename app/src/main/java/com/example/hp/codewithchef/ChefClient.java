package com.example.hp.codewithchef;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hp on 14-09-2018.
 */

public interface ChefClient {
  


    @Headers("Accept: application/json")
    @POST("/oauth/token")
    @FormUrlEncoded
    Call<AccessToken> getAccess(
        @Field("grant_type") String authorization,
        @Field("code") String code,
        @Field("client_id") String client_id,
        @Field("client_secret") String client_secret,
        @Field("redirect_uri") String redirect_uri
    );



    @Headers("Accept: application/json")
    @GET("/contests/{contestCode}")
    Call<Contest> getContestDetail(
            @Header("Authorization") String access_token ,
            @Path("contestCode") String contestCode
    );


}
//"grant_type": "authorization_code","code": "your_authorization_code"
//        ,"client_id":"your_client_id","client_secret":"your_client_secret"
//        ,"redirect_uri":"your_redirect_uri"