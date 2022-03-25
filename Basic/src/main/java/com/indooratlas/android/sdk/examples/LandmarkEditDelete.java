package com.indooratlas.android.sdk.examples;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.indooratlas.android.sdk.examples.geofence.EditActivity;
import com.indooratlas.android.sdk.examples.geofence.EditActivity2;

import java.util.ArrayList;

public class LandmarkEditDelete extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    WM_Adapter adapter;
    EditActivity dao;
    String key;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wm_edit_activity);
        database = FirebaseDatabase.getInstance();
        swipeRefreshLayout=findViewById(R.id.swip);
        recyclerView=findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter= new WM_Adapter(this);
        recyclerView.setAdapter(adapter);

        dao=new EditActivity();
        LoadData();



    }


    private void LoadData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Map/Landmark/");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                 This method is called once with the initial value and again
//                 whenever data at this location is updated.
//                Landmarks value = dataSnapshot.getValue(Landmarks.class);
                ArrayList<String> MSGS=new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//

                    String lat = ds.child("latLng/latitude").getValue().toString();
                    String lng = ds.child("latLng/longitude").getValue().toString();
                    String msg = ds.child("Title").getValue(String.class);
                    MSGS.add(msg);

                    double latitude = Double.parseDouble(lat);
                    double longitude = Double.parseDouble(lng);
                    LatLng loc = new LatLng(latitude, longitude);
//                    mMap.addMarker(new MarkerOptions().position(loc).title(msg).draggable(true).visible(false));
                }
                adapter.setItems(MSGS);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError error) {
//                 Failed to read value
            }
        });
    }


}