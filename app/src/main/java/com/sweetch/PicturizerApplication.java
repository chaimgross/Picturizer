package com.sweetch;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.sweetch.picturizer.Constants;
import com.sweetch.picturizer.network.PicturizerService;

public class PicturizerApplication extends Application {

    public static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        PicturizerService.initRetrofit();
        preferences = getSharedPreferences(Constants.PICTURIZER_PREFERENCES, Context.MODE_PRIVATE);
    }
}
