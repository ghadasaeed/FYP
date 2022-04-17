package com.example.fyp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.database.DatabaseReference;

import java.security.AccessControlContext;
import java.util.List;


public class categoryAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;


    List<UpdateProductModel> list;
    DatabaseReference databaseReference;
//    private List<UpdateProductModel> updateProductModelList;

    public categoryAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;

    }
//
//    public categoryAdapter(Context context, List<UpdateProductModel> list) {
//        this.context = context;
//        this.list = list;
//
//    }

//    public categoryAdapter(@NonNull FragmentManager fm,Context context, List<UpdateProductModel> list) {
//        super(fm);
//        this.context = context;
//        this.list = list;
//
//    }


    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    //public Fragment getItem(int position) {
        public Fragment getItem(int position) {
        switch (position){
            case 0:
                freash_products_Fragment Freash_products_fragment = new freash_products_Fragment();
                return Freash_products_fragment;
            case 1:
                exp_products_Fragment Exp_products_Fragment = new exp_products_Fragment();
                return Exp_products_Fragment;

            default:
                return null;
        }
    }


}
