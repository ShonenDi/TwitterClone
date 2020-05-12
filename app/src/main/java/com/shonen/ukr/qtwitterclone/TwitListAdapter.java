package com.shonen.ukr.qtwitterclone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class TwitListAdapter extends ArrayAdapter<ParseUser> {
    public TwitListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ParseUser> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
