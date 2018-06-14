package com.remoteclassnotes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    Button mMailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mMailButton = findViewById(R.id.btn_about_mail);
        mMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: prajithpremg@gmail.com"));
                startActivity(Intent.createChooser(mailIntent, "Contact developer"));
             }
        });
    }
}
