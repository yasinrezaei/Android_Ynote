package com.example.ynote;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import java.util.Random;
import androidx.core.app.NotificationCompat;

public class NotificaitonHelper extends ContextWrapper {

    Random rand = new Random(); //instance of random class
    int upperbound = 30;
    int int_random = rand.nextInt(upperbound);

    String channelID = String.valueOf(int_random);
    String channelName = String.valueOf(int_random);
    private NotificationManager manager ;

    public NotificaitonHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel mainChannel = new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_HIGH) ;
        mainChannel.enableLights(true);
        mainChannel.setLightColor(R.color.colorPrimary);
        mainChannel.enableVibration(true);
        getManager().createNotificationChannel(mainChannel);
    }
    public NotificationManager getManager() {
        if(manager == null){
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE) ;
        }
        return manager ;
    }
    public NotificationCompat.Builder getMainChannelNotification(String title,String message){
        return new NotificationCompat.Builder(getApplicationContext(),channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_notifications)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true);

    }

    public NotificationCompat.Builder getMainChannelLinkNotification(String title,String message,String link){
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link)) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,notificationIntent,PendingIntent.FLAG_ONE_SHOT) ;
        return new NotificationCompat.Builder(getApplicationContext(),channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_notifications)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

//                .addAction(R.mipmap.ic_launcher,"link",pendingIntent);

    }


}
