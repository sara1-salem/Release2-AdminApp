package com.indooratlas.android.sdk.examples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.indooratlas.android.sdk.examples.geofence.EditActivity2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WM_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<String> list = new ArrayList<String>();

    public WM_Adapter(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<String> WarningMsg) {
        list.addAll(WarningMsg);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_wm, parent, false);
        return new WarningVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WarningVH vh = (WarningVH) holder;
        String wm = list.get(position);
        vh.txt_name.setText(wm);
        holder.itemView.findViewById(R.id.Edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private Picasso getSnapshots() {
        return null;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




}