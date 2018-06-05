package com.remoteclassnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FolderSelectActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<String> subjectsList;
    private ListView mSubjectList;
    private SubjectAdapter mSubjectAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_select);

        mSubjectList = findViewById(R.id.list_subjects);
        mProgressBar = findViewById(R.id.progress_subject);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("subjects");
        subjectsList = new ArrayList<>();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String subject = dataSnapshot.getValue(String.class);
                subjectsList.add(subject);
                mSubjectAdapter = new SubjectAdapter(FolderSelectActivity.this, R.layout.item_subject, subjectsList);
                mSubjectList.setAdapter(mSubjectAdapter);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mSubjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FolderSelectActivity.this, DownloadActivity.class);
                intent.putExtra("SUBJECT_NAME", mSubjectAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
