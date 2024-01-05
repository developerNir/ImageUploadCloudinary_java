package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;

import java.util.Map;

public class MainActivity3 extends AppCompatActivity {


    Button MyButton;

    String publicId = "j5lwlo3y9dyjokwanyxa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        MyButton = findViewById(R.id.MyButton);


        MyButton.setOnClickListener(v -> {

            Toast.makeText(this, "Hello My name is nirapadka", Toast.LENGTH_SHORT).show();

        });


    }

}