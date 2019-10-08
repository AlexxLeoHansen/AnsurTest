package com.example.joseba.ansurtest;

//Thumb class to set and get valuable data

public class Thumbnail {
    private String url;
    private int id;
    private double latitude;
    private double longitude;

    public Thumbnail(){
    }

    public  Thumbnail(String thumbUrl, int thumbId, Double thumbLatitude, Double thumbLongitude){
            this.url = thumbUrl;
            this.id = thumbId;
            this.latitude = thumbLatitude;
            this.longitude = thumbLongitude;
    }

    public String getUrl(){
        return url;
    }
    public int getId(){
        return id;
    }
    public String getLatitude(){ return String.valueOf(latitude);}
    public String getLongitude(){ return String.valueOf(longitude);}

    public void setUrl(String url){this.url = url;}
    public void setId(int id){this.id = id;}
    public void setLatitude(double lat){this.latitude = lat;}
    public void setLongitude(double lon){this.longitude = lon;}

}
