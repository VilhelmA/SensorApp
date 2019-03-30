package com.example.sensorapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView x,y,z;
    private ConstraintLayout layout;
    static final float ALPHA = 0.2f;
    protected float[] accelVals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        x = findViewById(R.id.xacc);
        y = findViewById(R.id.yacc);
        z = findViewById(R.id.zacc);
        layout = findViewById(R.id.acclayout);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float ax, ay, az;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelVals = lowPass( event.values, accelVals );
        }

        ax = accelVals[0];
        ay = accelVals[1];
        az = accelVals[2];
        x.setText("X:" + ax);
        y.setText("Y:" + ay);
        z.setText("Z:" + az);

        layout.setBackgroundColor(Color.argb(255,
                Math.abs(Math.round(ax*25)),
                Math.abs(Math.round(ay*25)),
                Math.abs(Math.round(az*25))));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected float[] lowPass( float[] input, float[] output ) {
        if ( output == null ) return input;

        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }
}
