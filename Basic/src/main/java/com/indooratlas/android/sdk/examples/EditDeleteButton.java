package com.indooratlas.android.sdk.examples;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.indooratlas.android.sdk.examples.geofence.EditActivity2;
import com.indooratlas.android.sdk.examples.geofence.Landmarks;

public class EditDeleteButton extends AppCompatActivity {
    ImageButton Update,Delete;
    EditText Des;
    String key;
    String desc;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Landmarks title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_item_wm);
        key=getIntent().getStringExtra("key");
        desc=getIntent().getStringExtra("msg");
        Update=(ImageButton) findViewById(R.id.Edit);
        Delete=(ImageButton) findViewById(R.id.Delete);
        Des=(EditText) findViewById(R.id.txt_name);
        Des.setText(desc);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Map/WarningMsg/");

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
                String Title=title.getLTitle();
                showDeleteDataDialog(Title);

            }

        });

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
