package com.example.fyp.groups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fyp.R;

public class AddMembersPage extends AppCompatActivity {

    ImageView Backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members_page);
        Backbtn = findViewById(R.id.backbtn);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMembersPage.this, MembersPage.class);
                startActivity(intent);
            }
        });


    }
}