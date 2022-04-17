package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.fyp.groups.Groups_page;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    CardView food;
    Button GroupsPageBtn;
    ImageView SettingsPageBtn;
    FloatingActionButton AddBtn;
    RecyclerView recyclerView;
    private List<Category> list;
    DatabaseReference databaseReference;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //food = findViewById(R.id.food);
        GroupsPageBtn = findViewById(R.id.groupsPageBtn);
        SettingsPageBtn = findViewById(R.id.settingsPageBtn);
        AddBtn = findViewById(R.id.addButton);

        recyclerView = findViewById(R.id.recyclerViews);
        recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Category").child(userid);
        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(this,list);
        recyclerView.setAdapter(homeAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Category category = dataSnapshot1.getValue(Category.class);
                    list.add(category);

                }

                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(HomePage.this, AddCategoryPage.class);
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