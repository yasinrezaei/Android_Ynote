package com.example.ynote;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class EditNote extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    EditText edt_title,edt_text;
    Button btn_edit_data,btn_show_notes;
    TextView date,uptext,showdate,showtime;
    DataBaseHelper mydb;
    Bundle bundle;
    TextView tClock;
    String day_name;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);
        ////
        Intent intent = getIntent();
        day_name = intent.getStringExtra("daykey");


        ////

        mydb = new DataBaseHelper(this);
        configureView();
        //daryaft etelat vagti ke edit mikhad beshe
        bundle=getIntent().getExtras();

        uptext.setText("Edit Note");
        showdate.setText(bundle.getString("datekey"));
        showtime.setText(bundle.getString("timekey"));
        edt_title.setText(bundle.getString("titleKey"));
        edt_text.setText(bundle.getString("textKey"));
        //edt_link.setText(bundle.getString("linkKey"));


        Data();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(EditNote.this, EditNote.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
        String mydate=String.valueOf(myYear)+"/"+String.valueOf(myMonth)+"/"+String.valueOf(myday);

        showdate.setText(mydate);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        String mytime=String.valueOf(myHour)+" : "+String.valueOf(myMinute);
        showtime.setText(mytime);
    }

    public void Data(){
        ///
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        date.setText("Today : "+s);
        ///
        SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        tClock.setText(currentDateandTime);


        ///////add or edit data/////////////////

        btn_edit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///set day///
                Calendar c=Calendar.getInstance();
                int day_of_week=c.get(Calendar.DAY_OF_WEEK);

                ////////
                boolean isupdate=mydb.updateData(bundle.getString("idKey"),
                edt_title.getText().toString(),
                edt_text.getText().toString(),
                showdate.getText().toString(),
                showtime.getText().toString(),
                day_name);
                if(isupdate==true){
                    Toast.makeText(EditNote.this,"The note was successfully edit" ,Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(EditNote.this,MainActivity.class);
                    intent1.putExtra("day",day_name );
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(EditNote.this,"The note could not be edit successfully" ,Toast.LENGTH_SHORT).show();
                 }
                }


        });
        /////////////////////show notes recycler view in main activity/////////////////////

        btn_show_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditNote.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ///////////////date time picker////////////


        /////////////////////////////////////////////
    }



    public void configureView(){
        edt_title=(EditText) findViewById(R.id.edt_title);
        edt_text=(EditText)findViewById(R.id.edt_text);
        //edt_link=(EditText)findViewById(R.id.edt_link);

        btn_edit_data=(Button)findViewById(R.id.btn_edit_data);
        btn_show_notes=(Button)findViewById(R.id.btn_show_notes) ;


        date=(TextView)findViewById(R.id.date);
        showdate=(TextView)findViewById(R.id.showdate);
        showtime=(TextView)findViewById(R.id.showtime);
        tClock = (TextView) findViewById(R.id.clock);
        uptext = (TextView) findViewById(R.id.uptext);
    }
    public String name_of_day(int i){
        String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        return days[i-1];
    }
}
