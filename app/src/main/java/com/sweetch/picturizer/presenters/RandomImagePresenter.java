package com.sweetch.picturizer.presenters;

import android.graphics.Bitmap;

import com.sweetch.picturizer.ImageDataSource;
import com.sweetch.picturizer.tools.FileChooserManager;

public class RandomImagePresenter {

    private final FileChooserManager fileChooserManager;
    private final RandomImageInterface viewInterface;
    private final ImageDataSource imageDataSource;

    public RandomImagePresenter(RandomImageInterface imageInterface) {
        this.viewInterface = imageInterface;
        fileChooserManager = new FileChooserManager();
        imageDataSource = new ImageDataSource();
    }

    public void getImage() {
        String imageUrl = fileChooserManager.getFileName();
        imageDataSource.getRandomImage(imageUrl, new ImageDataSource.FetchImageListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                viewInterface.displayImage(bitmap);
            }

            @Override
            public void onFailure(String error) {
                viewInterface.displayError(error);
            }
        });
    }

    public interface RandomImageInterface {
        void displayImage(Bitmap bitmap);
        void displayError(String error);
    }
}
