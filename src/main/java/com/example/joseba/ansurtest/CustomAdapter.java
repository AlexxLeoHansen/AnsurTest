package com.example.joseba.ansurtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Activity mContext;
    protected ArrayList<Bitmap> images = new ArrayList<Bitmap>();

    static class ViewHolderItem{
        ImageView iv;
    }

    public CustomAdapter (Activity context){
        this.mContext = context;
    }

    @Override
    public int getCount() { return HttpClient.getJsonSize(); }

    @Override
    public Object getItem(int position) { return HttpClient.getThumbnail(position);    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder = null;

        if(convertView==null){
            //Inflate each grid item
                LayoutInflater inflater = mContext.getLayoutInflater();
                convertView = inflater.inflate(R.layout.g_item, parent, false);

                viewHolder = new ViewHolderItem();

            //fit the imgview for the first time into the viewholder
                viewHolder.iv = convertView.findViewById(R.id.pic);
                convertView.setTag(viewHolder);

        }else{
                viewHolder = (ViewHolderItem) convertView.getTag();
        }

        final ViewHolderItem finalViewHolder = viewHolder;

        //Asynctask to download the images passing viewholder, thumb id, url and a listener to
        //set the image when it's downloaded
        new DownloadImageTask(viewHolder.iv,
                HttpClient.getThumbnail(position).getId(),
                new DownloadImageTask.Listener() {
                    @Override
                    public void onImageDownloaded(Bitmap bitmap) {
                        finalViewHolder.iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onImageDownloadError(int imId) {
                        finalViewHolder.iv = null; Log.e("onImageDownloadError",
                                "Error downloading the image with id "+imId);
                    }

                }).execute(HttpClient.getThumbnail(position).getUrl());

        return convertView;
    }

}