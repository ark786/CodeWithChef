package com.example.hp.codewithchef;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String clientId="fb39c2e8b23387151173d6fef988c4b1";
    String client_secret="8a2a82094a3173bdf4c78689bdf5e639";
    String redirectUri="codewithchef://callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("onCreate","Whoaaaaaaaaaaaaaaaaaaaaaaaaa");
        Button b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=(Intent) new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.codechef.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUri+"&state=xyz"));
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume","Nhooooooooooooooooooooooooo");
        Uri uri=getIntent().getData();
        if(uri!=null&& uri.toString().startsWith(redirectUri))
        {
            String code=uri.getQueryParameter("code");

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
            httpClient.addInterceptor(logging);
            Retrofit.Builder builder= new Retrofit.Builder().
                    baseUrl("https://api.codechef.com").
                    addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build());
            final Retrofit retrofit=builder.build();
            final ChefClient client=retrofit.create(ChefClient.class);
            final Call<AccessToken> call = client.getAccess("authorization_code", code, clientId, client_secret, redirectUri);
            final AccessToken acc = new AccessToken();
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    AccessToken accessToken  =response.body();
                    if(response.code()==200) {
                        Toast.makeText(MainActivity.this, response.code()+" "+ accessToken.getAccess_token()+ "Yay"+ accessToken.getRefresh_token(),Toast.LENGTH_SHORT).show();
                    }


//                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this,  "Nhooooo",Toast.LENGTH_SHORT).show();

                }
            });



        }
    }
}
