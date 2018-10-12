package com.example.hp.codewithchef;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 14-09-2018.
 */

class AccessToken {
    @SerializedName("result")
    result  result=new result();


    public class result
    {
        @SerializedName("data")
        public  data data=new data();

        public class data
        {
            @SerializedName("access_token")
            String access_token;

//            @SerializedName("access_token")
//            String access_token;
//            @SerializedName("access_token")
//            String access_token;
//            @SerializedName("access_token")
//            String access_token;
            @SerializedName("refresh_token")
            String refresh_token;
        }
    }
    public String getAccess_token() {
        return result.data.access_token;
    }



    public String getRefresh_token() {
        return result.data.refresh_token;
    }


}
