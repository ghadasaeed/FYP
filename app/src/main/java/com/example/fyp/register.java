package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity {

    TextInputLayout Email, Password, ConPassword;
    Button Register, Login;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String password, confpassword, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Email = (TextInputLayout)findViewById(R.id.email);
        Password = (TextInputLayout)findViewById(R.id.password);
        ConPassword = (TextInputLayout)findViewById(R.id.confirmPassword);

        Register = (Button)findViewById(R.id.registerbtn);
        Login = (Button)findViewById(R.id.oldLogin);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        FAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                email = Email.getEditText().getText().toString().trim();
                password = Password.getEditText().getText().toString().trim();
                confpassword = ConPassword.getEditText().getText().toString().trim();

                if(isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(register.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait....");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("User ID", useridd);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        HashMap<String, String> hashMappp = new HashMap<>();
                                        hashMappp.put("EmailID", email);
                                        hashMappp.put("Password", password);
                                        hashMappp.put("ConfirmPassword", confpassword);


                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                                                            builder.setMessage("You have registered! Now please verify your email");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();

                                                                    Intent b = new Intent(register.this,HomePage.class);
                                                                    startActivity(b);

                                                                }
                                                            });
                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();
                                                        }else{
                                                            mDialog.dismiss();
                                                            ReusableCodeForAll.ShowAlert(register.this,"Error",task.getException().getMessage());
                                                        }
                                                    }
                                                });
                                            }
                                        });

                                    }
                                });
                            }else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(register.this,"Error",task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(register.this,login.class));
                finish();
            }
        });
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        Email.setErrorEnabled(false);
        Email.setError("");
        Password.setErrorEnabled(false);
        Password.setError("");
        ConPassword.setErrorEnabled(false);
        ConPassword.setError("");

        boolean isValid = false,isValidemail=false,isValidpassword=false,isValidconfpassword=false;
        if(TextUtils.isEmpty(email)){
            Email.setErrorEnabled(true);
            Email.setError("Email is Required");
        }else{
            if(email.matches(emailpattern)){
                isValidemail = true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(password)){
            Password.setErrorEnabled(true);
            Password.setError("Enter Password");
        }else{
            if(password.length()<8){
                Password.setErrorEnabled(true);
                Password.setError("Password is weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            ConPassword.setErrorEnabled(true);
            ConPassword.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                ConPassword.setErrorEnabled(true);
                ConPassword.setError("Password doesn't match");
            }else{
                isValidconfpassword = true;
            }
        }

        isValid = isValidconfpassword && isValidpassword && isValidemail && isValidemail;
        return isValid;
    }
}