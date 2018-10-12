package com.example.hp.codewithchef;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 19-09-2018.
 */

class Contest {
    @SerializedName("content")
    Content data= new Content();

    public class Content
    {
        @SerializedName("bannerFile")
        String dat;

    }

}
