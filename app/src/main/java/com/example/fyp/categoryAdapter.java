package com.example.fyp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.security.AccessControlContext;
import java.util.List;


public class categoryAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;
//    private List<UpdateProductModel> updateProductModelList;

    public categoryAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;

    }



    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
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
