package com.example.calculatorjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button basic_btn;
    Button adv_btn;
    Button about_btn;
    Button exit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basic_btn = findViewById(R.id.basic_btn);
        adv_btn = findViewById(R.id.buttonMultiply);
        about_btn = findViewById(R.id.buttonMinus);
        exit_btn = findViewById(R.id.buttonExit);

        basic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBasicCalculator();
            }
        });

        adv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedCalculator();
            }
        });

        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutPage();
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }

    public void openBasicCalculator(){
        Intent intent = new Intent(this, BasicActivity.class);
        startActivity(intent);
    }

    public void openAdvancedCalculator(){
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }

    public void openAboutPage(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}