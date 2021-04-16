package com.sweetch.picturizer.network;

import com.sweetch.picturizer.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class PicturizerService {

    private static PicturizerServiceInterface service;
    private static Retrofit retrofit;

    public static void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE + "/")
                .client(client)
                .build();
    }

    public static PicturizerServiceInterface getService() {
        if (service == null)
            service = retrofit.create(PicturizerServiceInterface.class);
        return service;
    }
}
