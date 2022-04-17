package com.example.fyp;

import android.content.Context;
import android.content.Intent;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
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
    private categoryAdapter2 packageViewAdapter;
//    private List<UpdateProductModel> updateProductModelList;
    ArrayList<UpdateProductModel> productList = new ArrayList<>();
    ////////////////
    ImageView imageView;
    TextView ProductName;
    TextView RemainigDays;
    float v=0;
//    RecyclerView recyclerView;
    DatabaseReference dataaa, databaseReference;

//    private List<UpdateProductModel> updateProductModelList;//
    String Email,Password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_freash_products_, container, false);

//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        updateProductModelList = new ArrayList<>();
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

//        imageView = root.findViewById(R.id.productImg);
//        ProductName = root.findViewById(R.id.productName);
//        RemainigDays = root.findViewById(R.id.remainingDays);
//
//        imageView.setTranslationY(300);
//        ProductName.setTranslationY(300);
//        RemainigDays.setTranslationY(300);
//
//        imageView.setAlpha(v);
//        ProductName.setAlpha(v);
//        RemainigDays.setAlpha(v);
//
//        imageView.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        ProductName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        RemainigDays.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        recyclerView = view.findViewById(R.id.recyclerView1);
//        populateData();
        UserProducts();
        packageViewAdapter = new categoryAdapter2(context, productList);
        recyclerView.setAdapter(packageViewAdapter);

    }

    private void UserProducts(){
        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(useridd);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //productList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UpdateProductModel updateProductModel = snapshot.getValue(UpdateProductModel.class);
                    productList.add(updateProductModel);

                }
//                adapter1 = new categoryAdapter(getContext(), updateProductModelList);
//                ViewPager.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });}


// class categoryAdapter2 extends RecyclerView.Adapter<categoryAdapter2.MyViewHolder> {
//
//
//    Context context;
//    List<UpdateProductModel> list;
//    DatabaseReference databaseReference;
//
//    public categoryAdapter2(Context context, List<UpdateProductModel> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public categoryAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
//        return new categoryAdapter2.MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull categoryAdapter2.MyViewHolder holder, int position) {
//
////        final UpdateProductModel updateProductModel = updateProductModellist.get(position);
//
//        final UpdateProductModel updateProductModel = list.get(position);
//        Glide.with(context).load(updateProductModel.getImageURL()).into(holder.imageView);
//
//        holder.ProductName.setText(updateProductModel.getProductName());
//        updateProductModel.getProductEXP();
//        updateProductModel.getRandomUID();
//        updateProductModel.getUserId();
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, CategoryPage.class);
////                intent.putExtra("Category",category.getRandomUID());
////                intent.putExtra("UserId",category.getUserId());
//
//
//                context.startActivity(intent);
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView Color, ProductName,Rday;
//        ImageView imageView;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imageView = itemView.findViewById(R.id.productImg1);
//            ProductName = itemView.findViewById(R.id.productName1);
//            Rday = itemView.findViewById(R.id.remainingDays1);
//
//        }
//    }


}