package com.indooratlas.android.sdk.examples.geofence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.indooratlas.android.sdk.examples.AddWarningMsg;
import com.indooratlas.android.sdk.examples.ListExamplesActivity;
import com.indooratlas.android.sdk.examples.R;

public class EditAndDelete extends AppCompatActivity {
    LatLng latlng;
    String title,key ;
    FirebaseDatabase database ;
    DatabaseReference MyRef,removeref;




    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_and_delete);
        title =  getIntent().getParcelableExtra("Title");
        latlng = (LatLng) getIntent().getParcelableExtra("location");

        database = FirebaseDatabase.getInstance();
        MyRef = database.getReference("Map/Landmark");

    }
    public void OnReturnClick(View V){
        Intent edit = new Intent(EditAndDelete.this, GeofenceMapsOverlayActivity.class);
        startActivity(edit);
    }






}
