package com.example.saleel.workmanagerexample;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {
    OneTimeWorkRequest oneTimeWorkRequest;
    final static String  KEY_INPUT ="Input_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data data = new Data.Builder().
                putString(KEY_INPUT,"Will pass input dat to to work").build();
        oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWork.class).build();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });

        final TextView textView = findViewById(R.id.textView);

      WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
              .observe(this, new Observer<WorkInfo>() {
                  @Override
                  public void onChanged(@Nullable WorkInfo workInfo) {
                      String status = workInfo.getState().name();
                      textView.append(status+"\n");
                  }
              });

    }
}