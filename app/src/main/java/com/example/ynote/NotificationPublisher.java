package com.example.ynote;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import java.util.Random;

public class NotificationPublisher  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extra = intent.getExtras();
        String title = extra.getString("title") ;
        String Text=extra.getString("text");
        String link = extra.getString("link","0");
        Random rand = new Random();
        int r = rand.nextInt(100);
        if(link.equals("0")){
            
            NotificaitonHelper notificationHelper = new NotificaitonHelper(context) ;
            NotificationCompat.Builder nb = notificationHelper.getMainChannelNotification(title,Text) ;
            notificationHelper.getManager().notify(r,nb.build());
        }
        else {
            NotificaitonHelper notificationHelper = new NotificaitonHelper(context) ;
            NotificationCompat.Builder nb = notificationHelper.getMainChannelLinkNotification(title,Text,link) ;
            notificationHelper.getManager().notify(1,nb.build());
        }

    }


    }

