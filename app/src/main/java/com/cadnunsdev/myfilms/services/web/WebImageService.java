package com.cadnunsdev.myfilms.services.web;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Tiago Silva on 11/08/2016.
 */

public class WebImageService extends AsyncTask<String, Void, Bitmap> {

    private final ImageView _imageView;

    public static void SetImage(ImageView imageView, String url){
        new WebImageService(imageView).execute(url);
    }

    private WebImageService(ImageView imageView){
        _imageView = imageView;
    }

//    public static Bitmap getImageFromUrl(String urlAdress) throws IOException {
//        URL newurl = new URL(urlAdress);
//        Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
//
//        return mIcon_val;
//    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String imageURL = strings[0];
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }
    protected void onPostExecute(Bitmap result) {
        _imageView.setImageBitmap(result);
    }
}
