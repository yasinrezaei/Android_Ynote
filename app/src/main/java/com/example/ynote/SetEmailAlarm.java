package com.example.ynote;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class SetEmailAlarm  extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    String timeText;
    String title,text,link,dayname;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setalarm);

        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        text = extras.getString("text");
        link = extras.getString("link");
        dayname = extras.getString("day");




        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        startAlarm(c);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificaitonHelper.class);
        intent.putExtra("title",title);
        intent.putExtra("text",text);
        intent.putExtra("link",link);


        Random rand = new Random();
        int r = rand.nextInt(100);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, r, intent, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        Objects.requireNonNull(alarmManager).setExact(AlarmManager.RTC_WAKEUP,
                c.getTimeInMillis(), pendingIntent);

        Intent intent1=new Intent(SetEmailAlarm.this,EmailActivity.class);
        intent.putExtra("day", dayname);
        startActivity(intent1);
    }
}
