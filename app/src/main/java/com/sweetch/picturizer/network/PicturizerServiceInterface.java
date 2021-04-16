package com.sweetch.picturizer.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface PicturizerServiceInterface {

    @Streaming
    @GET("{id}.zip")
    Call<ResponseBody> getZipFile(@Path(value = "id", encoded = true) String fileId);
}
