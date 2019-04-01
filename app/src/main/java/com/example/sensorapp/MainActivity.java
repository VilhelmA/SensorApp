package com.example.sensorapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="com.example.myfirstapp.MESSAGE";
    private EditText editText;
    private ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        cl = findViewById(R.id.main_layout);
    }

    public void verifyMessage(View view){
        String color = editText.getText().toString();
        switch (color.trim()){
            case "Red":
                cl.setBackgroundColor(Color.RED);
                break;
            case "Green":
                cl.setBackgroundColor(Color.GREEN);
                break;
            case "Blue":
                cl.setBackgroundColor(Color.BLUE);
                break;
            case "Yellow":
                cl.setBackgroundColor(Color.YELLOW);
                break;
            case "Black":
                cl.setBackgroundColor(Color.BLACK);
                break;
            default:
                cl.setBackgroundColor(Color.WHITE);
        }

    }

    public void showAccelerometer(View view){
        Intent intent = new Intent(this, AccelerometerActivity.class);
        startActivity(intent);
    }

    public void showCompass(View view){
        Intent intent = new Intent(this, CompassActivity.class);
        startActivity(intent);
    }
}
