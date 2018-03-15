package com.example.gmanni.arduinoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blynkClass bc = new blynkClass("30fd52bdfd024481a8fc7db4ee924a20");
        bc.getPinValue("D", 6);
        //prova p = new prova("30fd52bdfd024481a8fc7db4ee924a20");

    }
}
