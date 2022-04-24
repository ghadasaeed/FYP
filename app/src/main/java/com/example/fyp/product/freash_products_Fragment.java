package com.example.fyp.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Category;
import com.example.fyp.HomeAdapter;
import com.example.fyp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class freash_products_Fragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private categoryAdapter2 ViewAdapter;
   public List<ProductDetails> productList;
    float v=0;
    DatabaseReference databaseReference;
    SearchView searchView;

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
        searchView = view.findViewById(R.id.search);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        populateData();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
       // databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(userid).child(category);
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

//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String searchtext = resultsearcheview.getText().toString();
//                firebasesearch(searchtext);
//            }
//        });

//////////////////////////////////////
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                search(newText);
//                return true;
//            }
//        });
        /////////////////////////////////

    }
//    public void firebasesearch(String searchtext){
//        Query firebaseSearchQuery = mdatabaseReference.orderByChild("ProductName").startAt(searchtext).endAt(searchtext+"\uf8ff");
//        FirebaseRecyclerAdapter<Items, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Items, UsersViewHolder>
//                (  Items.class,
//                        R.layout.list_layout,
//                        UsersViewHolder.class,
//                        firebaseSearchQuery )
//        {
//            @Override
//            protected void populateViewHolder(UsersViewHolder viewHolder, Items model,int position){
//
//                viewHolder.setDetails(getApplicationContext(),model.getItembarcode(),model.getItemcategory(),model.getItemname(),model.getItemprice());
//            }
//        };
//
//        mrecyclerview.setAdapter(firebaseRecyclerAdapter);
//    }


    /////////////////////////////////////////
//        private void search(final String searchtext) {
//
//        ArrayList<ProductDetails> mylist = new ArrayList<>();
//        for (ProductDetails object : productList ) {
//            if (object.getProductName().toLowerCase().contains(searchtext.toLowerCase())) {
//                mylist.add(object);
//            }
//        }
//        ViewAdapter = new categoryAdapter2(getContext(), mylist);
//        recyclerView.setAdapter(ViewAdapter);
//
//    }
    //////////////////////////////////////
}