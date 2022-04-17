package com.example.fyp.groups;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fyp.Category;
import com.example.fyp.HomeAdapter;
import com.example.fyp.HomePage;
import com.example.fyp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Groups_page extends AppCompatActivity {

    Button Backbtn;
//    CardView Group1;
    FloatingActionButton AddBtn;
    RecyclerView recyclerView;
    private List<Group> list;
    DatabaseReference  databaseReference;
    GroupsAdapter groupsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_page);

        Backbtn = findViewById(R.id.backbtn);
//        Group1 = findViewById(R.id.group1);
        AddBtn = findViewById(R.id.addButton1);
        recyclerView = findViewById(R.id.recyclerViews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Group").child(userid);
        list = new ArrayList<>();
        groupsAdapter = new GroupsAdapter(this,list);
        recyclerView.setAdapter(groupsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Group group = dataSnapshot1.getValue(Group.class);
                    list.add(group);



                }
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                        Category category = snapshot1.getValue(Category.class);
//                        list.add(category);
//                    }
//                }


                groupsAdapter.notifyDataSetChanged();
//                adapter = new CustomerHomeAdapter(getContext(), updateDishModelList);
//                recyclerView.setAdapter(adapter);

//                homeAdapter = new HomeAdapter(this,list);
//                recyclerView.setAdapter(homeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

//        Group1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(Groups_page.this, Group_page.class);
//                startActivity(intent2);
//            }
//        });
    }
}