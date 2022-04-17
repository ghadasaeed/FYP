package com.example.fyp.product;

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
import com.example.fyp.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


public class categoryAdapter2 extends RecyclerView.Adapter<categoryAdapter2.MyViewHolder> {


    Context context;
    List<ProductDetails> list;
    private int width, height;
    DatabaseReference databaseReference;
public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView ProductName,Rday;
    ImageView imageView;
    View itemView1;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.productImg1);
        ProductName = (TextView)itemView.findViewById(R.id.productName1);
        Rday = (TextView)itemView.findViewById(R.id.remainingDays1);
        itemView1 = itemView;
    }
}

    public categoryAdapter2(Context context, List<ProductDetails> list) {
        this.context = context;
        this.list = list;
        this.width = 160;
        this.height = 100;
    }
    public categoryAdapter2(Context context, List<ProductDetails> list, int _width, int _height) {
        this.context = context;
        this.list = list;
        this.width = 160;
        this.height = 100;
    }

    @NonNull
    @Override
    public categoryAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
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
        final ProductDetails updateProductModel = list.get(position);
        Glide.with(context).load(updateProductModel.getImageURL()).into(holder.imageView);

        holder.ProductName.setText(updateProductModel.getProductName());
        holder.Rday.setText(updateProductModel.getProductEXP()+"Day");
        updateProductModel.getRandomUID();
        updateProductModel.getUserId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, AddProductPage.class);
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


}

