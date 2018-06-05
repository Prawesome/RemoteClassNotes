package com.remoteclassnotes;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by praji on 2/18/2018.
 */

public class FileAdapter extends ArrayAdapter<NoteFile> {

    FileAdapter(Context context, int resource, List<NoteFile> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_file, parent, false);
        }

        TextView mFileName = (TextView) view.findViewById(R.id.text_item_file_name);
        TextView mDescriptionField = view.findViewById(R.id.text_item_description);

        NoteFile file = getItem(position);

        if (file != null) {
            mFileName.setText(file.getFileName());
            mDescriptionField.setText(file.getFileDetails());
        }

        return view;
    }
}