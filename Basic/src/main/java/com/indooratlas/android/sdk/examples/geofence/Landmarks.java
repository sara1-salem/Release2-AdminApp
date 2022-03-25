package com.indooratlas.android.sdk.examples.geofence;

import com.google.android.gms.maps.model.LatLng;

public class Landmarks {

    public LatLng latLng;
    public String Title;
    //String LDes;


    public Landmarks(LatLng latlng, String title) {
        latLng = latlng;
        Title = title;
        // LDes = d;
    }


    public Landmarks(){

    }
public Landmarks(String title){
         Title=title;
}
    public String getLTitle() {
        return Title;
    }

    public void setTitle1(String title) {
        Title = title;
    }
}
