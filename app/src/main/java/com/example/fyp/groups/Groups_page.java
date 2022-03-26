package com.example.fyp.groups;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fyp.HomePage;
import com.example.fyp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Groups_page extends AppCompatActivity {

    Button Backbtn;
    CardView Group1;
    FloatingActionButton AddBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_page);

        Backbtn = findViewById(R.id.backbtn);
        Group1 = findViewById(R.id.group1);
        AddBtn = findViewById(R.id.addButton1);


        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Groups_page.this, AddGroupPage.class);
                startActivity(intent2);
            }
        });
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Groups_page.this, HomePage.class);
                startActivity(intent);
            }
        });

        Group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Groups_page.this, Group_page.class);
                startActivity(intent2);
            }
        });
    }
}