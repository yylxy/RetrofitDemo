package com.example.lyxs9.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RetrofitTest retrofitTest;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        retrofitTest = new RetrofitTest();
        findViewById(R.id.item0).setOnClickListener(this);
        findViewById(R.id.item1).setOnClickListener(this);
        findViewById(R.id.item2).setOnClickListener(this);
        findViewById(R.id.item3).setOnClickListener(this);
        findViewById(R.id.item4).setOnClickListener(this);
        findViewById(R.id.item5).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item0:
                finish();
                break;
            case R.id.item1:
                retrofitTest.test1();
                break;
            case R.id.item2:
                retrofitTest.test2();
                break;
            case R.id.item3:
                break;
            case R.id.item4:
                break;
            case R.id.item5:
                break;
        }
    }
    
    
}
