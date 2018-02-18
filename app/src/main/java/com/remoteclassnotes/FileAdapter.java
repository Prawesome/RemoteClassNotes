package com.remoteclassnotes;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by praji on 2/18/2018.
 */

public class FileAdapter extends ArrayAdapter<NoteFile> {
    public FileAdapter(Context context, int resource, List<NoteFile> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_file, parent, false);
        }

        TextView mFileName = (TextView) view.findViewById(R.id.text_item_file_name);
        TextView mDownloadUrl = (TextView) view.findViewById(R.id.text_item_url);

        NoteFile file = getItem(position);
        mFileName.setText(file.getFileName());
        mDownloadUrl.setText(file.getDownloadUrl());

        return view;
    }
}