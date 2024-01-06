package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;

import java.util.Arrays;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {


    Button MyButton;

    public static String publicId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // screen short disable ----------------------
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        MyButton = findViewById(R.id.MyButton);

        MyButton.setOnClickListener(v -> {

            Toast.makeText(this, publicId, Toast.LENGTH_SHORT).show();

        });


    }

}