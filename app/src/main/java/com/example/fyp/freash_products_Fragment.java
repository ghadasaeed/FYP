package com.example.fyp;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class freash_products_Fragment extends Fragment {

    ImageView imageView;
    TextView ProductName;
    TextView RemainigDays;
    float v=0;
    RecyclerView recyclerView;
    DatabaseReference dataaa, databaseReference;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<UpdateProductModel> updateProductModelList;//
    String Email,Password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_freash_products_, container, false);

//        recyclerView = findViewById(R.id.recycle_menu);
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
}