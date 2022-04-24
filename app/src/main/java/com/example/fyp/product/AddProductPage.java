package com.example.fyp.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
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
import com.squareup.picasso.Picasso;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    public String productCategory,productName, barcode, productExp, alarmState,shelfLife,images,exp="5";
    //need to extract the product category from the title of the category
    ImageButton imageButton;
    ImageView Backbtn;
    Button AddProductbtn;
    TextInputLayout ProductName, ProductBarCode, ProductEXP;
    Switch AlarmState;
    public Uri imageuri;
    private Uri mCropimageuri;
    int daysUntilExpired;

    String scanedbarcode;




    int startYear = 0, startMonth = 0, startDay = 0;
    String dateFinal;

    private DatePickerDialog datePickerDialog;


   // private static String JSON_URL = "https://api.barcodelookup.com/v3/products?barcode=3600542154598&formatted=y&key=zd81hc1hjuzcbh5w3umqwqm54j0qrv";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_product_page);
      //  initDatePicker();

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

//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date();
//        exp = dateFormat.format(date);

      //  dateFinal = todayDateString();
//        Date your_date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(your_date);
//        startYear = cal.get(Calendar.YEAR);
//        startMonth = cal.get(Calendar.MONTH);
//        startDay = cal.get(Calendar.DAY_OF_MONTH);



        //////////////////////////////////////////




         //////////////////////////////////////




//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String date1 = "20/05/1985";
//        String date2 = "22/05/1999";
//        date2 = productExp;
//        date1 = getTodaysDate();
//
//        try {
//            Date toDate = dateFormat.parse(date2);
//            Date fromDate = dateFormat.parse(date1);
//            long diff = toDate.getTime() - fromDate.getTime();
//            exp = toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        if (dateFinal.trim().length() < 4) {
//            errorStep++;
//            date.setError("Provide a specific date");
//        }

      //  ProductEXP.getEditText().setText(getTodaysDate());


//        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        try{
//            Date toDate = dateFormat.parse(ProductEXP.getEditText().getText().toString().trim());
//            Date fromDate = dateFormat.parse(getTodaysDate());
//            long diff = toDate.getTime() - fromDate.getTime();
//            exp= String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
////System.out.println(t);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    //    Duration.between(start, Instant.now());


        try {
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataaa = firebaseDatabase.getInstance().getReference("User").child(userid);
            dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User userc = dataSnapshot.getValue(User.class);
                    GetData getData = new GetData();
                    getData.execute();
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



//                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                            Date date = null;
//                            try {
//                                date = sdf.parse((ProductEXP.getEditText().getText().toString().trim()));
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(date);

//                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                            Date date = new Date();



//                            Date toDate = stringToDate((ProductEXP.getEditText().getText().toString().trim()));
//
//                            Date  fromDate = stringToDate(todayDateString());
//
//                            long diff = toDate.getTime() - fromDate.getTime();
//                                exp = String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
//                                productExp = exp;
//System.out.println(t);


                        //    productExp =String.valueOf(recalculateDaysUntilExpired(cal));

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
//
//    private String getTodaysDate()
//    {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month = month + 1;
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        return makeDateString(day, month, year);
//
//    }

//    public String todayDateString()
//    {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        return dateFormat.toString();
//    }
//    @Override
//    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//        startYear = year;
//        startMonth = monthOfYear;
//        startDay = dayOfMonth;
//        int monthAddOne = startMonth + 1;
//        String date = (startDay < 10 ? "0" + startDay : "" + startDay) + "/" +
//                (monthAddOne < 10 ? "0" + monthAddOne : "" + monthAddOne) + "/" +
//                startYear;
//        EditText product_date = findViewById(R.id.date);
//        product_date.setText(date);
//    }

//
//    public void showStartDatePicker(View v)
//    {
//        dpd = DatePickerDialog.newInstance(AddProduct.this, startYear, startMonth, startDay);
//        dpd.setOnDateSetListener(this);
//        dpd.show(getFragmentManager(), "startDatepickerdialog");
//        //dpd.show(getSupportFragmentManager(), "asd");
//        //dpd.show(getSupportFragmentManager(), "startDatepickerdialog");
//
//    }

//    private void initDatePicker()
//    {
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
//        {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day)
//            {
//                month = month + 1;
//                String date = makeDateString(day, month, year);
//                ProductEXP.getEditText().setText(date);
//            }
//        };
//
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        int style = AlertDialog.THEME_HOLO_LIGHT;
//
//        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
//        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//
//    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + month + " " + year;
    }

//    private String getMonthFormat(int month)
//    {
//        if(month == 1 || month == 01)
//            return "1";
//        if(month == 2|| month == 02)
//            return "2";
//        if(month == 3|| month == 03)
//            return "3";
//        if(month == 4|| month == 04)
//            return "4";
//        if(month == 5|| month == 05)
//            return "5";
//        if(month == 6|| month == 06)
//            return "6";
//        if(month == 7|| month == 07)
//            return "7";
//        if(month == 8 || month == 08)
//            return "8";
//        if(month == 9 || month == 09)
//            return "9";
////        if(month == 10)
////            return "10";
////        if(month == 11)
////            return "11";
////        if(month == 12)
////            return "12";
//
//        //default should never happen
//        return "1";
//    }

//    private String getMonthFormat(int month)
//    {
//        if(month == 1)
//            return "JAN";
//        if(month == 2)
//            return "FEB";
//        if(month == 3)
//            return "MAR";
//        if(month == 4)
//            return "APR";
//        if(month == 5)
//            return "MAY";
//        if(month == 6)
//            return "JUN";
//        if(month == 7)
//            return "JUL";
//        if(month == 8)
//            return "AUG";
//        if(month == 9)
//            return "SEP";
//        if(month == 10)
//            return "OCT";
//        if(month == 11)
//            return "NOV";
//        if(month == 12)
//            return "DEC";
//
//        //default should never happen
//        return "JAN";
//    }

//    public void openDatePicker(View view)
//    {
//        datePickerDialog.show();
//    }
    public class GetData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {

            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
      //              url = new URL(JSON_URL);
      //              urlConnection = (HttpURLConnection) url.openConnection();
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
                    Glide.with(AddProductPage.this).load(images).into(imageButton);




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
        if (TextUtils.isEmpty(productExp)) {
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
            final ProgressDialog progressDialog = new ProgressDialog(AddProductPage.this);
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
 ////////////////
 private void updatedesc() {
     final ProgressDialog progressDialog = new ProgressDialog(AddProductPage.this);
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
                             Toast.makeText(AddProductPage.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                             //add finish the page after adding the product successfully
                             finish();
                         }
                     });
                 }

    public int recalculateDaysUntilExpired(Calendar cal1) {
        // double timeBetweenDates = expday.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        double timeBetweenDates = cal1.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();

        return daysUntilExpired = (int) Math.ceil((timeBetweenDates / 1000 / 60 / 60 / 24));
    }

    private Date stringToDate(String aDate) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy");
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

}

