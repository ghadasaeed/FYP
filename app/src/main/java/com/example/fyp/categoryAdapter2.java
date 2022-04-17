package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class categoryAdapter2 extends RecyclerView.Adapter<categoryAdapter2.MyViewHolder> {


    Context context;
    ArrayList<UpdateProductModel> list;
//    DatabaseReference databaseReference;
    DatabaseReference dataaa, databaseReference;
//    String userid;

    public categoryAdapter2(Context context, ArrayList<UpdateProductModel> list) {
        this.context = context;
        this.list = list;
    }

//    public categoryAdapter2(String userid) {
//        this.userid = userid;
//
//    }

    @NonNull
    @Override
    public categoryAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
        return new categoryAdapter2.MyViewHolder(v);

//        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        databaseReference = FirebaseDatabase.getInstance().getReference("UpdateProductModel").child(userid);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//
//                    UpdateProductModel updateProductModel = dataSnapshot1.getValue(UpdateProductModel.class);
//                    list.add(updateProductModel);
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

    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter2.MyViewHolder holder, int position) {

//        final UpdateProductModel updateProductModel = updateProductModellist.get(position);
        try{
        final UpdateProductModel updateProductModel = list.get(position);
        Glide.with(context).load(updateProductModel.getImageURL()).into(holder.imageView);

        holder.ProductName.setText(updateProductModel.getProductName());
        holder.Rday.setText(updateProductModel.getProductEXP());

        updateProductModel.getRandomUID();
        updateProductModel.getUserId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CategoryPage.class);
//                intent.putExtra("Category",category.getRandomUID());
//                intent.putExtra("UserId",category.getUserId());


                context.startActivity(intent);
            }
        });
        }catch (Exception e){e.printStackTrace();}


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Color, ProductName,Rday;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productImg1);
            ProductName = itemView.findViewById(R.id.productName1);
            Rday = itemView.findViewById(R.id.remainingDays1);

        }
    }
}

