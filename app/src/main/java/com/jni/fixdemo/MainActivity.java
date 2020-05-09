package com.jni.fixdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        Button btnExecute = findViewById(R.id.btn_execute);
        Button btnFix = findViewById(R.id.btn_fix);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator();
            }
        });
        btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fix();
            }
        });
    }

    private void calculator() {
        Calculator calculator = new Calculator();
        tvResult.setText(String.valueOf(calculator.calculator()));
    }

    private void fix() {
        File file = new File(Environment.getExternalStorageDirectory(), "fix.dex");
        DexManager dexManager = new DexManager(this);
        dexManager.load(file);
    }
}
