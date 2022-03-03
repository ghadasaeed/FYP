package com.example.fyp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanningPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanning_page);

        scanCode();

    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(  this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats (IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder( this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton( "Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();}
                    }).setNegativeButton("finish", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
            } else {
                Toast.makeText(this,"No Results", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}