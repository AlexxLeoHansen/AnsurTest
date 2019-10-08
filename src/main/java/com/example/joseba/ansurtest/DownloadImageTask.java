package com.example.joseba.ansurtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bm;
        int imageId;
        Listener listener;

        public DownloadImageTask(ImageView bitIm, int imageId, Listener listener) {
            this.bm = bitIm;
            this.imageId = imageId;
            this.listener = listener;
        }

        //Download the img from the URLs and get the Bitmap
        protected Bitmap doInBackground(String... urls) {
            Bitmap mThumb = null;
            try {
                InputStream is = (InputStream) new URL(urls[0]).getContent();
                mThumb = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mThumb;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                listener.onImageDownloaded(result);
            } else {
                listener.onImageDownloadError(imageId);
            }
        }
    //listen when the image is downloaded
    public interface Listener {
        void onImageDownloaded(final Bitmap bitmap);
        void onImageDownloadError(final int imId);
    }

}