package com.example.fyp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fyp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    TextView textView;
    TextView textView2;
    ConstraintLayout curve;
    ImageView img;
    Animation bottomAnim;
    Animation topAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //intro
        setContentView(R.layout.activity_intro);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        img = findViewById(R.id.logo);

        img.setAnimation(topAnim);
        textView2.setAnimation(bottomAnim);
        textView.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                //Call next screen
                Intent intent=new Intent(MainActivity.this,login.class);
                // Attach all the elements those you want to animate in design
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(img,"logo_image");
                pairs[1]=new Pair<View, String>(textView,"logo_text");
                //wrap the call in API level 21 or higher
                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        }, 3000);



















        ////////////////////////////////////////////////

//        textView = findViewById(R.id.textView);
//        textView2 = findViewById(R.id.textView2);
//        curve = findViewById(R.id.curve);
//
//        textView.animate().alpha(0f).setDuration(0);
//        textView2.animate().alpha(0f).setDuration(0);
//        curve.animate().alpha(0f).setDuration(0);
//
//        curve.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                textView.animate().alpha(1f).setDuration(800);
//                textView2.animate().alpha(1f).setDuration(800);
//            }
//        });


//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


}