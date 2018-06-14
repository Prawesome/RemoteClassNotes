package com.remoteclassnotes;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ConstraintLayout mRootLayout;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_select);

        mRootLayout = findViewById(R.id.layout_folder_select);
        mRootLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

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
                mRootLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
                actionBar.show();
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

        //TODO: GET NOTIFICATION MESSAGE PAYLOAD AND START SUBJECT ACTIVITY
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Utils.logout();
                Utils.unSubscribeFromNotification();
                return true;
            case R.id.action_about:
                startActivity(new Intent(FolderSelectActivity.this, AboutActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(FolderSelectActivity.this, SettingsActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
