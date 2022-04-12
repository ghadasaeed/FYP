package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.model.Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class AddProductPage extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference dataaa;
    FirebaseAuth FAuth;
    StorageReference ref;
    String UserId;
    String RandomUId;
    String Email,Password;
    Spinner ShelfLife;
    String productCategory,productName, barcode, productExp, alarm,shelfLife;
    //need to extract the product category from the title of the category
    ImageButton imageButton;
    ImageView Backbtn;
    Button AddProductbtn;
    TextInputLayout ProductName, ProductBarCode, ProductEXP;
    Switch AlarmState;
    Uri imageuri;
    private Uri mCropimageuri;
    //need to add the shelf life buttons or choises
    String URL = "/api/1234567890123";

    ArrayList<Model> dataList = new ArrayList<>();
    RecyclerView myRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_product_page);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference("ProductDetails");
        ShelfLife = (Spinner) findViewById(R.id.shelfLife); // add to the database
        AddProductbtn = (Button) findViewById(R.id.addbtn);
        ProductName = (TextInputLayout)findViewById(R.id.productName);
        ProductBarCode = (TextInputLayout)findViewById(R.id.barcode);
        ProductEXP = (TextInputLayout)findViewById(R.id.productExp);
        AlarmState = (Switch)findViewById(R.id.alarm);
        Backbtn = findViewById(R.id.backbtn);


//        String URL = "/api/1234567890123";
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        JsonObjectRequest objectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                URL, null,
//                new Response.Listener<JSONObject>(){
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.e("Rest Response",response.toString());
//                    }
//                },
//            new Response.ErrorListener(){
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("Rest Response",error.toString());
//                }
//            }
//        );
//
//        requestQueue.add(objectRequest);




        try {
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataaa = firebaseDatabase.getInstance().getReference("User").child(userid);
            dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User userc = dataSnapshot.getValue(User.class);

                    Email = userc.getEmailId();
                    Password = userc.getPassword();

                    imageButton = (ImageButton) findViewById(R.id.imageupload);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSelectImageClick(v);
                        }
                    });


                    AddProductbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            shelfLife = ShelfLife.getSelectedItem().toString().trim();
                            productName = ProductName.getEditText().getText().toString().trim();
                            barcode = ProductBarCode.getEditText().getText().toString().trim();
                            productExp = ProductEXP.getEditText().getText().toString().trim();
                            alarm = AlarmState.getText().toString().trim();

                            if (isValid()) {
                                uploadImage();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {

            Log.e("Errrrrr: ", e.getMessage());
        }


        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductPage.this, CategoryPage.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValid() {
        ProductName.setErrorEnabled(false);
        ProductName.setError("");
        ProductEXP.setErrorEnabled(false);
        ProductEXP.setError("");


        boolean isValiDescription = false, isvalidQuantity = false, isvalid = false;
        if (TextUtils.isEmpty(productName)) {
            ProductName.setErrorEnabled(true);
            ProductName.setError("Product Name is Required");

        } else {

            ProductName.setError(null);
            isValiDescription = true;
        }
        if (TextUtils.isEmpty(productExp)) {
            ProductEXP.setErrorEnabled(true);
            ProductEXP.setError("Product EXP is Required");
        } else {
            isvalidQuantity = true;
        }

        isvalid = (isValiDescription && isvalidQuantity ) ? true : false;

        return isvalid;
    }

    private void uploadImage() {

        if (imageuri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(AddProductPage.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            RandomUId = UUID.randomUUID().toString();
            ref = storageReference.child(RandomUId);
            UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ProductDetails info = new ProductDetails( productName, barcode, productExp, alarm, shelfLife, String.valueOf(uri), RandomUId, UserId);
                            firebaseDatabase.getInstance().getReference("ProductDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId)
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(AddProductPage.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                                    //add finish the page after adding the product successfully
                                    finish();
                                }
                            });
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressDialog.dismiss();
                    Toast.makeText(AddProductPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }
    }

    private void onSelectImageClick(View v) {

        CropImage.startPickImageActivity(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageuri = CropImage.getPickImageResultUri(this, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
                mCropimageuri = imageuri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

            } else {

                startCropImageActivity(imageuri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageButton) findViewById(R.id.imageupload)).setImageURI(result.getUri());
                Toast.makeText(this, "Cropped Successfully", Toast.LENGTH_SHORT).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed" + result.getError(), Toast.LENGTH_SHORT).show();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mCropimageuri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCropImageActivity(mCropimageuri);
        } else {
            Toast.makeText(this, "cancelling,required permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void startCropImageActivity(Uri imageuri) {

        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);


    }

//    //calling the API
//    private void json(){
//
//        String url ="/api/1234567890123";
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        JSONArray jsonArray = response.getJSONArray();
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//    }

    public void parseApiData(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                Log.e("Res : ",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}