package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fyp.groups.Groups_page;

public class HomePage extends AppCompatActivity {

    CardView food;
    Button GroupsPageBtn;
    ImageView SettingsPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        food = findViewById(R.id.food);
        GroupsPageBtn = findViewById(R.id.groupsPageBtn);
        SettingsPageBtn = findViewById(R.id.settingsPageBtn);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomePage.this, CategoryPage.class);
                startActivity(intent2);
            }
        });
        GroupsPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(HomePage.this, Groups_page.class);
                startActivity(intent3);
            }
        });

        SettingsPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(HomePage.this, SettingPage.class);
                startActivity(intent4);
            }
        });


    }
}