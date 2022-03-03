package com.example.fyp.groups;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class GroupAdapter extends FragmentPagerAdapter{

    private Context context;
    int totalTabs;

    public GroupAdapter(FragmentManager fm, Context context, int totalTabs) {
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
                Group_freash_products_Fragment Group_freash_products_fragment = new Group_freash_products_Fragment();
                return Group_freash_products_fragment;
            case 1:
                Group_Exp_products_Fragment Group_exp_products_fragment = new Group_Exp_products_Fragment();
                return Group_exp_products_fragment;

            default:
                return null;
        }
    }
}
