package com.example.fyp.groups;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.AddCategoryPage;
import com.example.fyp.HomePage;
import com.example.fyp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MembersPage extends AppCompatActivity {

    Button Backbtn;
    FloatingActionButton AddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_page);
        Backbtn = findViewById(R.id.backbtn);
        AddBtn = findViewById(R.id.addButton);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MembersPage.this, AddMembersPage.class);
                startActivity(intent2);
            }
        });

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembersPage.this, Group_page.class);
                startActivity(intent);
            }
        });
    }
}