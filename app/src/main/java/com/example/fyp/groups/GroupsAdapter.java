package com.example.fyp.groups;

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

import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder>{

    Context context ;
    List<Group> list;
    DatabaseReference databaseReference;

    public GroupsAdapter(Context context, List<Group> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GroupsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.group_list,parent,false);
        return new GroupsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.MyViewHolder holder, int position) {


        final Group group = list.get(position);
        Glide.with(context).load(group.getImageURL()).into(holder.imageView);

        holder.GroupName.setText(group.getGroupName());
        group.getRandomUID();
        group.getUserId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, Group_page.class);
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

        TextView GroupName;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.groupImg);
           GroupName =itemView.findViewById(R.id.groupName);


        }
    }
}
