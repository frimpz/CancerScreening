package com.drasare.cancerscreening.retrofit;

import android.se.omapi.Session;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://escape-cervical-api-test.us-east-2.elasticbeanstalk.com/api/";

    public static  Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();

        // okhttpClientBuilder.addInterceptor(new TokenRenewInterceptor());
        // return okhttpClientBuilder.build();
    }


    public static class TokenRenewInterceptor implements Interceptor {
        private Session session;

        public TokenRenewInterceptor(Session session) {
            this.session = session;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            // if 'x-auth-token' is available into the response header
            // save the new token into session.The header key can be
            // different upon implementation of backend.
            String newToken = response.header("x-auth-token");
            if (newToken != null) {
                // session.saveToken(newToken);
            }

            return response;
        }
    }
}
