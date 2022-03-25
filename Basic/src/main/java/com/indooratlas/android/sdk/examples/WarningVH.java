package com.indooratlas.android.sdk.examples;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

public class WarningVH extends RecyclerView.ViewHolder {
    public TextView txt_name,txt_option;

    public WarningVH(@NonNull View itemView) {
        super(itemView);
        txt_name=itemView.findViewById(R.id.txt_name);
        txt_option=itemView.findViewById(R.id.txt_option);

    }

    public TextView getTxt_name() {
        return txt_name;
    }
}