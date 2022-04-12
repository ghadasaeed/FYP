package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fyp.groups.Groups_page;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePage extends AppCompatActivity {

    CardView food;
    Button GroupsPageBtn;
    ImageView SettingsPageBtn;
    FloatingActionButton AddBtn;

//    private FirebaseAuth firebaseAuth;
//    RecyclerView mrecyclerview;
//    DatabaseReference mdatabaseReference;
//    private int counttotalnoofitem =0;

    RecyclerView recyclerView;
    private List<Category> list;
    String Email;
    DatabaseReference dataaa, databaseReference;
    SwipeRefreshLayout swipeRefreshLayout;
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

//        ////////////////////////////////////////////////////////////////////////////////////////////////////
        recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Category").child(userid);
        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(this,list);
        recyclerView.setAdapter(homeAdapter);
// the problem is here

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    Category category = dataSnapshot1.getValue(Category.class);
                    list.add(category);



                }
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                        Category category = snapshot1.getValue(Category.class);
//                        list.add(category);
//                    }
//                }


                homeAdapter.notifyDataSetChanged();
//                adapter = new CustomerHomeAdapter(getContext(), updateDishModelList);
//                recyclerView.setAdapter(adapter);

//                homeAdapter = new HomeAdapter(this,list);
//                recyclerView.setAdapter(homeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
//        updateDishModelList = new ArrayList<>();

//
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
//      //  swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.green);

//        swipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
//                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                dataaa = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
//                dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        User user = dataSnapshot.getValue(User.class);
//                        Email = user.getEmail();
//                        customermenu();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });



//        firebaseAuth = FirebaseAuth.getInstance();
//        final FirebaseUser users = firebaseAuth.getCurrentUser();
//        String finaluser=users.getEmail();
//        String resultemail = finaluser.replace(".","");
//        mdatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(resultemail).child("ProductDetails");
//        mrecyclerview = findViewById(R.id.recyclerViews);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        mrecyclerview.setLayoutManager(manager);
//        mrecyclerview.setHasFixedSize(true);
//        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
//
//        mdatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                int sum=0;
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
//                    Object category = map.get("itemprice");
//                    int pValue = Integer.parseInt(String.valueOf(price));
//                    sum += pValue;
//                    totalnoofsum.setText(String.valueOf(sum));
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent2 = new Intent(HomePage.this, CategoryPage.class);
//                startActivity(intent2);
//            }
//        });
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
//    @Override
//    public void onRefresh() {
//
//        customermenu();
//    }
//    private void customermenu() {
//
//        swipeRefreshLayout.setRefreshing(true);
//        databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(UserId);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                updateDishModelList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                        UpdateProductModel updateDishModel = snapshot1.getValue(UpdateProductModel.class);
//                        updateDishModelList.add(updateDishModel);
//                    }
//                }
////                adapter = new CustomerHomeAdapter(getContext(), updateDishModelList);
////                recyclerView.setAdapter(adapter);
////                swipeRefreshLayout.setRefreshing(false);
//                final UpdateProductModel updateDishModel=updateDishModelList.get(position);
//                Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
//                holder.Dishname.setText(updateDishModel.getDishes());
//                updateDishModel.getRandomUID();
//                updateDishModel.getUserId();
//                holder.price.setText("Price: RM " + updateDishModel.getPrice());
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent=new Intent(mcontext,OrderDish.class);
//                        intent.putExtra("FoodMenu",updateDishModel.getRandomUID());
//                        intent.putExtra("ChefId",updateDishModel.getChefId());
//
//
//                        mcontext.startActivity(intent);
//                    }
//                });
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return updateProductModellist.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView imageView;
//        TextView Dishname,price;
//        ElegantNumberButton additem;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imageView=itemView.findViewById(R.id.menu_image);
//            Dishname=itemView.findViewById(R.id.dishname);
//            price=itemView.findViewById(R.id.dishprice);
//            additem=itemView.findViewById(R.id.number_btn);
//
//
//        }
//    }





//    @Override
//    protected  void  onStart() {
//        super.onStart();
//
//        FirebaseRecyclerAdapter<ProductDetails> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Items, scanItemsActivity.UsersViewHolder>
//                (  Items.class,
//                        R.layout.list_layout,
//                        scanItemsActivity.UsersViewHolder.class,
//                        mdatabaseReference )
//        {
//            @Override
//            protected void populateViewHolder(scanItemsActivity.UsersViewHolder viewHolder, Items model, int position){
//
//                viewHolder.setDetails(getApplicationContext(),model.getItembarcode(),model.getItemcategory(),model.getItemname(),model.getItemprice());
//            }
//        };
//
//        mrecyclerview.setAdapter(firebaseRecyclerAdapter);
//    }
}