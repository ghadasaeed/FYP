package com.example.fyp.product;

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


public class CategoryPage extends AppCompatActivity {

    TabLayout tabLayout;
    FloatingActionButton addButton;
    ImageView Backbtn;
    float v=0;
    ViewPager viewPager ;
    private categoryAdapter adapter;
    categoryAdapter2 adapter2;
    SearchView searchView;

  //  DatabaseReference dataaa, databaseReference;
  //  private List<UpdateProductModel> updateProductModelList;//
   // String Email,Password;

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

        //        recyclerView = findViewById(R.id.recyclerViews);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        updateProductModelList = new ArrayList<>();
//        adapter2 = new categoryAdapter2(this,updateProductModelList);
//        recyclerView.setAdapter(adapter2);


        // recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        databaseReference = FirebaseDatabase.getInstance().getReference("UpdateProductModel").child(userid);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                updateProductModelList.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//
//                    UpdateProductModel updateProductModel = dataSnapshot1.getValue(UpdateProductModel.class);
//                    updateProductModelList.add(updateProductModel);
//
//
//
//                }
////                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
////                        Category category = snapshot1.getValue(Category.class);
////                        list.add(category);
////                    }
////                }
//
//
////                adapter2.notifyDataSetChanged();
////                adapter = new CustomerHomeAdapter(getContext(), updateDishModelList);
////                recyclerView.setAdapter(adapter);
//
////                homeAdapter = new HomeAdapter(this,list);
////                recyclerView.setAdapter(homeAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        dataaa = FirebaseDatabase.getInstance().getReference("User").child(userid);
//
//        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                User userc = dataSnapshot.getValue(User.class);
//                Email = userc.getEmailId();
//                Password = userc.getPassword();
//                UserProducts();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

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


               // Intent intent = new Intent(CategoryPage.this, BarcodeScanningPage.class);
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

    }

//    private void UserProducts(){
//        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(useridd);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                updateProductModelList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    UpdateProductModel updateProductModel = snapshot.getValue(UpdateProductModel.class);
//                    updateProductModelList.add(updateProductModel);
//
//                }
////                adapter1 = new categoryAdapter(getContext(), updateProductModelList);
////                ViewPager.setAdapter(adapter1);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}