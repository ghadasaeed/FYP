package com.example.fyp.groups;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fyp.AddProductPage;
import com.example.fyp.CategoryPage;
import com.example.fyp.HomePage;
import com.example.fyp.R;
import com.example.fyp.categoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class Group_page extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton addButton;
    ImageView Backbtn,MembersImg;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        addButton = findViewById(R.id.addButton);

        tabLayout.addTab(tabLayout.newTab().setText("Fresh"));
        tabLayout.addTab(tabLayout.newTab().setText("EXP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Backbtn = findViewById(R.id.backbtn);
        MembersImg = findViewById(R.id.membersImg);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Group_page.this, Groups_page.class);
                startActivity(intent);
            }
        });

        MembersImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Group_page.this, MembersPage.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                // Intent intent = new Intent(CategoryPage.this, BarcodeScanningPage.class);
                Intent intent = new Intent(Group_page.this, AddProductPage.class);

                startActivity(intent);
            }
        });


        final categoryAdapter adapter = new categoryAdapter(getSupportFragmentManager(),this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabUnselected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabReselected: " + tab.getPosition());
            }
        });
        tabLayout.setTranslationY(300);

        tabLayout.setAlpha(v);

        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();




    }
}