package com.indooratlas.android.sdk.examples.geofence;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.indooratlas.android.sdk.examples.R;

import java.util.ArrayList;
import java.util.List;


public class EditActivity2 extends Activity {
    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng latlng;
    List<Landmarks> landmarks = new ArrayList<>();
    int LMid = 0;


    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = mDatabase.getReference();
    DatabaseReference  push = myRef.push();
    String puch_id = push.getKey();
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_for_warning_msg);
        latlng = (LatLng) getIntent().getParcelableExtra("location");


        final EditText title1 = (EditText) findViewById(R.id.T1);
         boton = (Button) findViewById(R.id.save);
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
                        myRef.child("Map").child("WarningMsg").push().setValue(L);
                        Toast.makeText(EditActivity2.this, "The Warning Message is added! ", Toast.LENGTH_SHORT).show();

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


        Landmarks war_edit=(Landmarks) getIntent().getSerializableExtra("Edit");
        if (war_edit != null){
            boton.setText("Update");
            title1.setText(war_edit.getLTitle());

        }
        else {
            boton.setText("Submit");
        }
    }

    public Query get(String key){
        return myRef.orderByKey();
    }
    public Task<Void> remove(String key){
        return myRef.child(key).removeValue();
    }


    // public void writeNewLandmark(int lmid, LatLng lng, String T) {


    // }
public void update(String key,Landmarks l){
        myRef.child(key).setValue(l);

}
public void Delete(String key){
    myRef.child(key).setValue(null);
}
}