package com.remoteclassnotes;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SubjectAdapter extends ArrayAdapter<String> {

    public SubjectAdapter(@NonNull Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_subject, parent, false);
        }

        TextView mSubjectName = convertView.findViewById(R.id.text_subject);
        mSubjectName.setText(getItem(position));

        return convertView;
    }
}
