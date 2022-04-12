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
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{


    Context context ;
    List<Category> list;
    DatabaseReference databaseReference;

    public HomeAdapter(Context context, List<Category> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        final UpdateProductModel updateProductModel = updateProductModellist.get(position);

        final Category category = list.get(position);
       Glide.with(context).load(category.getImageURL()).into(holder.imageView);

        holder.CategoryName.setText(category.getCategoryName());
        category.getRandomUID();
        category.getUserId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,CategoryPage.class);
//                intent.putExtra("Category",category.getRandomUID());
//                intent.putExtra("UserId",category.getUserId());


                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Color, CategoryName;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.categoryImg);
            CategoryName =itemView.findViewById(R.id.categoryName);


        }
    }

}
