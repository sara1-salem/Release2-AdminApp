package com.indooratlas.android.sdk.examples.geofence;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.indooratlas.android.sdk.examples.R;

import java.util.ArrayList;
import java.util.List;


public class EditActivity extends Activity {
    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng latlng;
    List<Landmarks> landmarks = new ArrayList<>();
    int LMid = 0;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference();
    DatabaseReference  push = myRef.push();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editactivity);
        latlng = (LatLng) getIntent().getParcelableExtra("location");

        final EditText title1 = (EditText) findViewById(R.id.T1);
        Button boton = (Button) findViewById(R.id.save);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                MarkerOptions marker = new MarkerOptions().position(latlng);
                if (title1.getText() != null) {
                    marker.title(title1.getText().toString());
                    landmarks.add(new Landmarks(latlng,title1.getText().toString()));
                    LMid = landmarks.size();
                    for (int i = 0; i < landmarks.size(); i++) {
                        Landmarks L = landmarks.get(i);
                        // myRef.child("Map").child("Landmark").setValue(L);
                        myRef.child("Map").child("Landmark").push().setValue(L);

                        Toast.makeText(EditActivity.this, "The Landmark is added! ", Toast.LENGTH_SHORT).show();
                    }
                    //writeNewLandmark(LMid, latlng, title1.getText().toString());
                }
                //add landmark to landmarks arraylist


                Intent resultIntent = new Intent();
                resultIntent.putExtra("marker", marker);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        arrayList.add(latlng);
    }




}