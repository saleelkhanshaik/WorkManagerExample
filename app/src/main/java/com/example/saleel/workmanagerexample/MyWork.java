package com.example.saleel.workmanagerexample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;



public class MyWork extends Worker {


    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        showNotification("Hey I am Your work","Work is finished");
        return Result.success();
    }

    public void showNotification(String title, String description) {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("Channel_ID", "Channel_Name", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"Channel_ID")
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.mipmap.ic_launcher);
        manager.notify(1,builder.build());

    }
}
