package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    Button callRegister, login_btn,forgotpassword;
    ImageView image;
    TextInputLayout email, password;
    String Email, Password;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        try{
        //Hooks
        callRegister = findViewById(R.id.newAccount);
        image = findViewById(R.id.logo);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.Loginbtn);
        forgotpassword = findViewById(R.id.forgetPasswordbtn);
        FAuth = FirebaseAuth.getInstance();

            forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(login.this,ForgotPassword.class));
                    finish();
                }
            });

        callRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(email, "email");
                pairs[2] = new Pair<View, String>(password, "password_tran");
                pairs[3] = new Pair<View, String>(callRegister, "login_Register_tran");
                pairs[4] = new Pair<View, String>(login_btn, "button_tran");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = email.getEditText().getText().toString().trim();
                Password = password.getEditText().getText().toString().trim();

                if (isValid()){

                    final ProgressDialog mDialog = new ProgressDialog(login.this);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setCancelable(false);
                    mDialog.setMessage("Login Please Wait........");
                    mDialog.show();

                    FAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                mDialog.dismiss();

                                if (FAuth.getCurrentUser().isEmailVerified()){
                                    mDialog.dismiss();
                                    Toast.makeText(login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                    Intent Z = new Intent(login.this,HomePage.class);
                                    startActivity(Z);
                                    finish();

                                }else{
                                    ReusableCodeForAll.ShowAlert(login.this,"Verification Failed", "You Have Not Verified Your Email");
                                }
                            }else{
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(login.this,"Error", task.getException().getMessage());
                            }
                        }
                    });
                }

            }
        });



        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){

        email.setErrorEnabled(false);
        email.setError("");
        password.setErrorEnabled(false);
        password.setError("");

        boolean isvalid=false, isvalidemail=false, isvalidpassword=false;
        if (TextUtils.isEmpty(Email)){
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }
        else{
            if (Email.matches(emailpattern)){
                isvalidemail=true;
            }else{
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if (TextUtils.isEmpty(Password)){

            password.setErrorEnabled(true);
            password.setError("Password is Required");
        }else{
            isvalidpassword=true;
        }
        isvalid= isvalidemail && isvalidpassword;
        return isvalid;
    }
}