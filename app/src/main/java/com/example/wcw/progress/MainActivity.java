package com.example.wcw.progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.wcw.progresslibrary.IProgressBar;

public class MainActivity extends AppCompatActivity {

    private IProgressBar iProgressBar, iProgressBar2, iProgressBar3, iProgressBar4;
    private int count = 0;
    private int max = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iProgressBar = findViewById(R.id.ipb);
        iProgressBar2 = findViewById(R.id.ipb2);
        iProgressBar3 = findViewById(R.id.ipb3);
        iProgressBar4 = findViewById(R.id.ipb4);
        iProgressBar.setDesText("¥1340.09元").setDesMax(60).start();
        iProgressBar2.setDesText("¥155.40元　每月递减0.89元").setDesMax(90).start();
        iProgressBar3.setDesText("¥155万元").setDesMax(70).start();
        iProgressBar4.setDesText("¥1.59万元").setDesMax(50).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iProgressBar.stop();
        iProgressBar2.stop();
        iProgressBar3.stop();
        iProgressBar4.stop();
    }
}
