package com.example.joseba.ansurtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class CustomAsync extends AsyncTask<String, Integer, ArrayList<Thumbnail>> implements AdapterView.OnItemClickListener {

    ArrayList<Thumbnail> data = null;
    Activity mContext;
    View rv;
    static boolean dataReady = false;

    //Fetch the data in a background thread
    public CustomAsync(Activity Context){
        mContext = Context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Thumbnail> doInBackground(String... strings) {
        try {
            //Fetch data locally
            HttpClient.createConn();
            data=HttpClient.readJson();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<Thumbnail> arrayList) {
        super.onPostExecute(arrayList);

        dataReady = true;
        //Set the grid after getting the Thumb urls
        GridView gridView = (GridView) mContext.findViewById(R.id.grid);
        // Set the our customAdapter
        gridView.setAdapter(new CustomAdapter(mContext));


        //Setting Google Map Intent
        gridView.setOnItemClickListener(this);
    }

    //Start Maps on Thumbnail location
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Create a Uri from an intent string. Use the result to create an Intent.

        final String lat = HttpClient.getThumbnail(position).getLatitude();
        final String lon = HttpClient.getThumbnail(position).getLongitude();

        //Parsing to "geo: lat,lon?=lat,lon" format
        Uri mapsUri = Uri.parse("geo:"+lat +","+lon+"?q="+lat+","+lon);

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapsUri);

        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        if (mapIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(mapIntent);
        }
        else
            Toast.makeText(mContext, "Google Maps isn't available", Toast.LENGTH_SHORT).show();

    }
}
