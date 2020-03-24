package com.example.assignment10;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapteri extends ArrayAdapter<Bitmap> {
    private Context context;
    ArrayList<Bitmap> dataset;

    Adapteri(@NonNull Context context, int resource, @NonNull List objects){
        super(context, resource, objects);
        this.context = context;
        this.dataset = (ArrayList<Bitmap>) objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.bitmap_layout, parent, false);
        LinearLayout linearLayout = (LinearLayout) v;

        ImageView kuva = v.findViewById(R.id.imageView);

        kuva.setImageBitmap(dataset.get(position));

        return v;
    }
}
