package com.example.fyp.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fyp.HomePage;
import com.example.fyp.R;
import com.example.fyp.User;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UpdateDelete_Product extends AppCompatActivity {



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
    public String productCategory,productName, barcode, productExp, alarmState,shelfLife,images;
    //need to extract the product category from the title of the category
    ImageButton imageButton;
    ImageView Backbtn;
    Button EditProductbtn ,DeleteProductbtn;
    TextInputLayout ProductName, ProductBarCode, ProductEXP;
    Switch AlarmState;
    public Uri imageuri;
    private Uri mCropimageuri;



    int startYear = 0, startMonth = 0, startDay = 0;
    String dateFinal;

    private DatePickerDialog datePickerDialog;


    // private static String JSON_URL = "https://api.barcodelookup.com/v3/products?barcode=6281006408647&formatted=y&key=jfz7w0r62nu5jyznef6225tdgihwpx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_product);

        //  initDatePicker();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        FAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference("ProductDetails");
        ShelfLife = (Spinner) findViewById(R.id.shelfLife); // add to the database
        EditProductbtn = (Button) findViewById(R.id.editbtn);
        DeleteProductbtn= (Button) findViewById(R.id.deletebtn);

        ProductName = (TextInputLayout)findViewById(R.id.productName);
        ProductBarCode = (TextInputLayout)findViewById(R.id.barcode);
        ProductEXP = (TextInputLayout)findViewById(R.id.productExp);
        AlarmState = (Switch)findViewById(R.id.alarm);
        Backbtn = findViewById(R.id.backbtn);

        //  dateFinal = todayDateString();
        Date your_date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(your_date);
        startYear = cal.get(Calendar.YEAR);
        startMonth = cal.get(Calendar.MONTH);
        startDay = cal.get(Calendar.DAY_OF_MONTH);

//        if (dateFinal.trim().length() < 4) {
//            errorStep++;
//            date.setError("Provide a specific date");
//        }

        //  ProductEXP.getEditText().setText(getTodaysDate());

        try {
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataaa = firebaseDatabase.getInstance().getReference("User").child(userid);
            dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User userc = dataSnapshot.getValue(User.class);
                    UpdateDelete_Product.GetData getData = new UpdateDelete_Product.GetData();
                    getData.execute();
                    Email = userc.getEmailId();
                    Password = userc.getPassword();

//                    imageButton = (ImageButton) findViewById(R.id.imageupload);
//                    imageButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            onSelectImageClick(v);
//                        }
//                    });


                    EditProductbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            shelfLife = ShelfLife.getSelectedItem().toString().trim();
                            productName = ProductName.getEditText().getText().toString().trim();
                            barcode = ProductBarCode.getEditText().getText().toString().trim();
                            productExp = ProductEXP.getEditText().getText().toString().trim();
                            alarmState = AlarmState.getText().toString().trim();
                            if (isValid()) {
                                if (imageuri != null) {
                                    uploadImage();
                                } else {
                                    updatedesc();
                                }

                            }
                        }
                    });

                    DeleteProductbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDelete_Product.this);
                            builder.setMessage("Are you sure you want to Delete Dish");
                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    firebaseDatabase.getInstance().getReference("ProductDetails").child(FirebaseAuth.getInstance().
                                            getCurrentUser().getUid()).child(RandomUId).removeValue();
                                    AlertDialog.Builder food = new AlertDialog.Builder(UpdateDelete_Product.this);
                                    food.setMessage("Your Product has been Deleted");
                                    food.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            startActivity(new Intent(UpdateDelete_Product.this, HomePage.class));
                                        }
                                    });
                                    AlertDialog alertt = food.create();
                                    alertt.show();


                                }
                            });      builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });


//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                progressDialog = new ProgressDialog(UpdateDelete_Dish.this);
//                databaseReference = FirebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(State).child(City).child(Area).child(useridd).child(ID);
//                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        UpdateDishModel updateDishModel = dataSnapshot.getValue(UpdateDishModel.class);
//
//                        desc.getEditText().setText(updateDishModel.getDescription());
//                        qty.getEditText().setText(updateDishModel.getQuantity());
//                        Dishname.setText("Dish name: " + updateDishModel.getDishes());
//                        dishes = updateDishModel.getDishes();
//                        pri.getEditText().setText(updateDishModel.getPrice());
//                        Glide.with(UpdateDelete_Dish.this).load(updateDishModel.getImageURL()).into(imageButton);
//                        dburi = updateDishModel.getImageURL();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


                FAuth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getInstance().getReference("FoodSupplyDetails");
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectImageClick(v);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /////////////////////////////////

        } catch (Exception e) {

            Log.e("Errrrrr: ", e.getMessage());
        }


        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateDelete_Product.this, CategoryPage.class);
                startActivity(intent);
            }
        });


    }

//    private void updatedesc(String uri) {
//        ChefId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        FoodSupplyDetails info = new FoodSupplyDetails(dishes, quantity, price, description, uri, ID, ChefId);
//        firebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(State).child(City).child(Area)
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(ID)
//                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                progressDialog.dismiss();
//                Toast.makeText(UpdateDelete_Dish.this, "Dish Updated Successfully", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    public class GetData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {

            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
//                    url = new URL(JSON_URL);
//                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {


            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("products");
                ArrayList<String> arrayList = new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    ProductDetails model = new ProductDetails();
                    model.setProductName(jsonObject1.getString("brand"));
                    model.setProductBarCode(jsonObject1.getString("barcode_number"));
                    model.setImageURL(jsonObject1.getString("images"));
                    //   movieList.add(model);


                    ProductName.getEditText().setText(model.getProductName());
                    ProductBarCode.getEditText().setText(model.getProductBarCode());

                    //  imageuri.setText(String.valueOf(model.getImageURL()));

//.getUri()
                    //[https://images.barcodelookup.com/id]
                    // https://images.barcodelookup.com/id https:\/\/images.barcodelookup.com\/23612\/236126957-1.jpg
                    //  Glide.with(AddProductPage.this).load(model.getImageURL()).into(imageButton);
                    //  ((ImageButton) findViewById(R.id.imageupload)).setImageURI(model.getImageURL());

                    images = model.getImageURL();
                    images = images.replaceAll("[\\\\]*", "");
                    images = images.replaceAll("[\\[\\]\"]*", "");
                    Glide.with(UpdateDelete_Product.this).load(images).into(imageButton);




                    // imageuri =Uri.parse(images);
                    //should make an auto fill of the data
                }

                //      super.onPostExecute(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //   PutDataIntoRecyclerView( movieList);


        }
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
        if (TextUtils.isEmpty(productExp)||productExp.trim().length() < 6) {
            ProductEXP.setErrorEnabled(true);
            ProductEXP.setError("Full Product EXP is Required");
        } else {
            isvalidQuantity = true;
        }

        isvalid = (isValiDescription && isvalidQuantity ) ? true : false;

        return isvalid;
    }

    private void uploadImage() {

        if (imageuri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(UpdateDelete_Product.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            RandomUId = UUID.randomUUID().toString();
            ref = storageReference.child(RandomUId);
            UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            Picasso.get()
//                    .load("https:\\/\\/images.barcodelookup.com\\/23612\\/236126957-1.jpg")
//                    .resize(90,90)
//                    .into(imageButton );

            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ProductDetails info = new ProductDetails( productName, barcode, productExp, alarmState, shelfLife,
                                    String.valueOf(uri), RandomUId, UserId);
                            //    String.valueOf(uri), RandomUId, UserId);
                            firebaseDatabase.getInstance().getReference("ProductDetails").child(FirebaseAuth.getInstance().
                                    getCurrentUser().getUid()).child(RandomUId)
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.dismiss();
                                    Toast.makeText(UpdateDelete_Product.this, "Product added successfully", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(UpdateDelete_Product.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
    ////////////////
    private void updatedesc() {
        final ProgressDialog progressDialog = new ProgressDialog(UpdateDelete_Product.this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        RandomUId = UUID.randomUUID().toString();
        ref = storageReference.child(RandomUId);
        UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ProductDetails info = new ProductDetails(productName, barcode, productExp, alarmState, shelfLife,
                images , RandomUId, UserId);
        firebaseDatabase.getInstance().getReference("ProductDetails").child(FirebaseAuth.getInstance().
                getCurrentUser().getUid()).child(RandomUId)
                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(UpdateDelete_Product.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                //add finish the page after adding the product successfully
                finish();
            }
        });
    }


}
