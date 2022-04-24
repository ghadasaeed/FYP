package com.example.fyp.product;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.example.fyp.HomePage;
import com.example.fyp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class CategoryPage extends AppCompatActivity {

    TabLayout tabLayout;
    FloatingActionButton addButton;
    ImageView Backbtn;
    float v=0;
    ViewPager viewPager ;
    private categoryAdapter adapter;
    categoryAdapter2 adapter2;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category_page);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager =findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Fresh"));
        tabLayout.addTab(tabLayout.newTab().setText("EXP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Backbtn = findViewById(R.id.backbtn);
        addButton = findViewById(R.id.addButton);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryPage.this, HomePage.class);
                startActivity(intent);
            }
        });
        
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


             //   Intent intent = new Intent(CategoryPage.this, BarcodeScanningPage.class);
               Intent intent = new Intent(CategoryPage.this, AddProductPage.class);

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


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                search(newText);
//                return true;
//            }
//        });
    }

//    private void search(final String searchtext) {
//
//        ArrayList<ProductDetails> mylist = new ArrayList<>();
//        for (ProductDetails object : productList ) {
//            if (object.getProductName().toLowerCase().contains(searchtext.toLowerCase())) {
//                mylist.add(object);
//            }
//        }
//        adapter2 = new categoryAdapter2(this, mylist);
//        viewPager.setAdapter(adapter);
//
//    }



}