package com.indooratlas.android.sdk.examples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EditWMSG extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_for_warning_msg);
    }

    public void OnReturnClick(View V){
        Intent myintent = new Intent(EditWMSG.this, AddWarningMsg.class);
        startActivity(myintent);
    }
}
