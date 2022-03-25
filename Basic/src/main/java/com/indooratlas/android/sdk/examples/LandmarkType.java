package com.indooratlas.android.sdk.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.indooratlas.android.sdk.examples.geofence.GeofenceMapsOverlayActivity;

public class LandmarkType extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landmark_type);


    }
    public void onClick(View v)
    {
        Intent myintent = new Intent(LandmarkType.this, GeofenceMapsOverlayActivity.class);
        // show map to admin -- DONE
        startActivity(myintent);
    }
}
