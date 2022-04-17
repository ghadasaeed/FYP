package com.example.fyp.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.R;

public class exp_products_Fragment extends Fragment {

    ImageView imageView;
    TextView ProductName;
    TextView RemainigDays;
    float v=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_exp_products_, container, false);

//        imageView = root.findViewById(R.id.productImg1);
//        ProductName = root.findViewById(R.id.productName1);
//        RemainigDays = root.findViewById(R.id.remainingDays1);
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
}