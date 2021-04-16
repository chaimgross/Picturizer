package com.sweetch.picturizer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.github.clemp6r.futuroid.Async;
import com.github.clemp6r.futuroid.Future;
import com.github.clemp6r.futuroid.FutureCallback;
import com.sweetch.picturizer.network.PicturizerService;
import com.sweetch.picturizer.tools.FileChooserManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDataSource {

    private final FileChooserManager fileChooserManager;
    private FetchImageListener listener;

    public ImageDataSource() {
        this.fileChooserManager = new FileChooserManager();
    }

    /**
     * Make network call to get zipped file
     * @param fileName
     * @param listener
     */
    public void getRandomImage(String fileName, FetchImageListener listener) {
        this.listener = listener;
        Call<ResponseBody> responseBodyCall = PicturizerService.getService().getZipFile(fileName);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    handleImageStream(response.body());
                }
                else {
                    listener.onFailure("Cannot receive image from server");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure("Cannot receive image from server");
            }
        });
    }


    private void handleImageStream(ResponseBody body) {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(body.byteStream()));
        try {
            zipInputStream.getNextEntry();
        } catch (IOException e) {
            listener.onFailure("Cannot read image from zip");
        }
        parseZip(zipInputStream).addCallback(new FutureCallback<Bitmap>() {
            @Override
            public void onSuccess(Bitmap result) {
                returnImage(result);
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure("Cannot parse data to image");
            }
        });
    }

    private void returnImage(Bitmap bitmap) {
        fileChooserManager.incrementIndex();
        listener.onSuccess(bitmap);
    }

    private Future<Bitmap> parseZip(ZipInputStream zipInputStream) {
        return Async.submit(() -> BitmapFactory.decodeStream(zipInputStream));
    }

    public interface FetchImageListener {
        void onSuccess(Bitmap bitmap);
        void onFailure(String error);
    }
}
