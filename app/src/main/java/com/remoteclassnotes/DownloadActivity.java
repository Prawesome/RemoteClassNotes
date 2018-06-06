package com.remoteclassnotes;

import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends AppCompatActivity {

    private ListView mFilesList;
    private List<NoteFile> filesList;
    private FileAdapter mAdapter;
    private ProgressBar mProgressBar;
    String subjectName;

    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Intent intent = getIntent();
        subjectName = intent.getStringExtra("SUBJECT_NAME");

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("files");

        mProgressBar = findViewById(R.id.progress_download);
        mFilesList = findViewById(R.id.list_download_items);
        filesList = new ArrayList<NoteFile>();

        firebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                NoteFile noteFile = dataSnapshot.getValue(NoteFile.class);
                if(noteFile.getSubjectName().equals(subjectName)) {
                    filesList.add(noteFile);
                    mAdapter = new FileAdapter(DownloadActivity.this, R.layout.item_file, filesList);
                    mFilesList.setAdapter(mAdapter);
                    mProgressBar.setVisibility(View.GONE);
                }
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

        mFilesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoteFile noteFile = mAdapter.getItem(position);

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(DownloadActivity.this, Uri.parse(noteFile.getDownloadUrl()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null) {
                    FirebaseAuth.getInstance().signOut();
                }
                FirebaseMessaging.getInstance().unsubscribeFromTopic("RCN");
                startActivity(new Intent(DownloadActivity.this, LoginActivity.class));
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
}
