package com.example.fyp.groups;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.HomePage;
import com.example.fyp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MembersPage extends AppCompatActivity {

    Button Backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_page);
        Backbtn = findViewById(R.id.backbtn);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembersPage.this, Group_page.class);
                startActivity(intent);
            }
        });
    }
}