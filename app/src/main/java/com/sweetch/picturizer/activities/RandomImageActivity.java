package com.sweetch.picturizer.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sweetch.picturizer.R;
import com.sweetch.picturizer.presenters.RandomImagePresenter;

public class RandomImageActivity extends AppCompatActivity implements RandomImagePresenter.RandomImageInterface {

    private RandomImagePresenter presenter;
    private ImageView randomImageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomImageIV = findViewById(R.id.random_image_iv);

        presenter = new RandomImagePresenter(this);

        // Can be moved to onResume if want new image every time app is opened
        presenter.getImage();
    }

    @Override
    public void displayImage(Bitmap bitmap) {
        randomImageIV.setImageBitmap(bitmap);
    }

    @Override
    public void displayError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}