package com.example.fyp.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Category;
import com.example.fyp.HomeAdapter;
import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class freash_products_Fragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private categoryAdapter2 ViewAdapter;
    private List<ProductDetails> productList;
    float v=0;
    DatabaseReference databaseReference;

//    private List<UpdateProductModel> updateProductModelList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_freash_products_, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.recyclerView1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        populateData();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(userid);
        productList = new ArrayList<>();
//        UserProducts();
        ViewAdapter = new categoryAdapter2(context, productList);
        recyclerView.setAdapter(ViewAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    ProductDetails productDetails = dataSnapshot1.getValue(ProductDetails.class);
                    productList.add(productDetails);

                }

                ViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    private void UserProducts(){
//        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(useridd);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //productList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ProductDetails updateProductModel = snapshot.getValue(ProductDetails.class);
//                    productList.add(updateProductModel);
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
//        });}

}