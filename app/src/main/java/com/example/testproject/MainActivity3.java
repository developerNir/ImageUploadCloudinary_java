package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity3 extends AppCompatActivity {

    ImageView myRotate;
    Button MyButton;
    Animation rotateAnimation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        myRotate = findViewById(R.id.RotateImage);
//        MyButton = findViewById(R.id.buttonRotate);
        rotateAnimation = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.rotate_anim);


        MyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRotate.startAnimation(rotateAnimation);
            }
        });


    }
}