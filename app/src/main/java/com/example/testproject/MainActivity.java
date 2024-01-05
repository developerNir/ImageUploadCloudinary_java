package com.example.testproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.utils.ObjectUtils;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ProgressBar progressBar;
    Button button, buttonthree;
    TextView textView;
    Uri ImagePath;


    private static final String TAG = "Upload ###";
    //String url = "https://github.com/square/picasso/raw/master/website/static/sample.png";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // introduce Button and process bar-------------------------
        imageView = findViewById(R.id.imageSelectPoint);
        progressBar = findViewById(R.id.progress_circular);
        button = findViewById(R.id.buttonClick);
        textView = findViewById(R.id.ResponseView);
        buttonthree = findViewById(R.id.buttonThree);


        progressBar.setVisibility(View.GONE);
        // load image with picasso in android ----------------------



        // select Image --------------------------------------------
        imageView.setOnClickListener((View v) -> {

            requestPermissions();

        });

        buttonthree.setOnClickListener((View v)->{
            startActivity(new Intent(MainActivity.this, MainActivity3.class));
        });

        Map<String, Object> options = new HashMap<>();
//        options.put("use_filename", "true");
        options.put("folder", "image/");
        options.put("tags", "animal nirapadakpal234");


        // button click and upload image to Cloudinary.com --------
        button.setOnClickListener(v ->{
            progressBar.setVisibility(View.VISIBLE);
            // cloudinary mediaManager and Callback function ------
            String result = MediaManager.get().upload(ImagePath).options(options).callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {
                    Log.d(TAG, "onStart: Upload image Starting");

                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {
                    Log.d(TAG, "OnProgress: Upload image onProgress");
                }

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    Log.d(TAG, "onSuccess: Upload image onSuccess");
                    progressBar.setVisibility(View.GONE);
                    // i get my url from cloud image url -----------------
                    textView.setText(resultData.toString());
                    MainActivity2.myUrl = (String) resultData.get("url");
                    Log.d(TAG, "onSuccess: Response"+resultData);
//                    String ImageUrl = (String) resultData.get("url");
                    //Toast.makeText(MainActivity.this, ImageUrl, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));

                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Log.d(TAG, "OnError: Upload image Error info "+error);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {
                    Log.d(TAG, "OnReschedule: Upload image Error Information : "+error);
                    progressBar.setVisibility(View.GONE);
                }
            }).dispatch();

            textView.setText(result);
            Log.d(TAG, "response: "+result);
        });



        initConfig();


    }
// init config for cloudinary api key and cloud name or api secret---
    private void initConfig() {
        Map config = new HashMap<>();
        config.put("cloud_name", "dwlcudfef");
        config.put("api_key", "771444917859754");
        config.put("api_secret","xMkyT9yw2wUeNNEpSb1AdHCe5hU");
//        config.put("source", true);
        MediaManager.init(this, config);
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            SelectImage();


        }else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },1);
        }

    }


    // select image insight form the gallery

    private void SelectImage() {
       Intent newIntent = new Intent();
       newIntent.setType("image/*");
       newIntent.setAction(Intent.ACTION_GET_CONTENT);
//       startActivityForResult(newIntent, 1);
        startActivityIntent.launch(newIntent);

    }



    // onActivityResult Override Method ----------------------------

            ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Add same code that you want to add in onActivityResult method
                        if (result.getResultCode()==Activity.RESULT_OK){
                            Intent data = result.getData();
                            ImagePath = data.getData();
                            Picasso.get().load(ImagePath).into(imageView);
                        }
                    }
                });
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//// how to show image in imageview
//        if(data.getData()!=null)
//        {
//            ImagePath = data.getData();
//            Picasso.get().load(ImagePath).into(imageView);
//        }
//    }
}