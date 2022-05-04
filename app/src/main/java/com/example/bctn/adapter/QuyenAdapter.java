package com.example.bctn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bctn.R;
import com.example.bctn.domain.quanan;

import java.util.List;

public class QuyenAdapter extends ArrayAdapter<String> {
    public QuyenAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_select, parent, false);
        TextView txtV_Spinner_Item = convertView.findViewById(R.id.txtV_Spinner_Select);

        String quyen = this.getItem(position);
        if (quyen != null) {
            txtV_Spinner_Item.setText(quyen);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false);
        TextView txtV_Spinner_Item = convertView.findViewById(R.id.txtV_Spinner_Item);

        String quyen = this.getItem(position);
        if (quyen != null) {
            txtV_Spinner_Item.setText(quyen);
        }
        return convertView;
    }
}
