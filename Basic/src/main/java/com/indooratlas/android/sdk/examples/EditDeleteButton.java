package com.indooratlas.android.sdk.examples;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.indooratlas.android.sdk.examples.geofence.EditActivity2;
import com.indooratlas.android.sdk.examples.geofence.Landmarks;

public class EditDeleteButton extends AppCompatActivity {
    Button Update,Delete;
    EditText Des;
    String key;
    String desc;
    DatabaseReference myRef,myRefl;
    FirebaseDatabase database;
    Landmarks title;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Landmarks landmarks = (Landmarks) getIntent().getSerializableExtra("Landmark");
        db = FirebaseFirestore.getInstance();


        key=getIntent().getStringExtra("key");
        desc=getIntent().getStringExtra("msg");
        Des.setText(desc);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Map/WarningMsg/");
        myRefl = database.getReference("Map/Landmark/");

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Landmarks wm=new Landmarks();
                wm.setTitle1(Des.getText().toString());
                new EditActivity2().update(key,wm);
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMarker(landmarks);

            }

        });

    }
    private void deleteMarker(Landmarks landmarks){

        db.collection("Map/Landmark").document(landmarks.getLTitle())
                .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(EditDeleteButton.this,"Landmark is deleted! ",Toast.LENGTH_SHORT).show();

                }
            }
        });

        myRefl=FirebaseDatabase.getInstance().getReference("Map/Landmark/").child(key);
        myRefl.removeValue();
        Toast.makeText(getApplicationContext(),"Landmark is deleted succesfully",Toast.LENGTH_LONG).show();
    }

    public void showDeleteDataDialog(String currentTitle){
        AlertDialog.Builder builder=new AlertDialog.Builder(EditDeleteButton.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete this warning message?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Query mQuery=myRef.orderByChild("WarningMsg").equalTo(currentTitle);
                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds:snapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(EditDeleteButton.this, "warning message deleted successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditDeleteButton.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();

    }
}
