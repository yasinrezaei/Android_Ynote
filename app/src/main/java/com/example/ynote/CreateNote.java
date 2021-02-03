package com.example.ynote;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

import static androidx.appcompat.app.AlertDialog.*;

public class CreateNote extends AppCompatActivity{
    EditText edt_title,edt_text,edt_link;
    Button btn_add_data,btn_show_notes;
    ImageButton btn_datetimepicker;
    TextView date,uptext;
    DataBaseHelper mydb;
    Bundle bundle;
    TextView tClock;
    String day_name;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);
        ////
        Bundle extras = getIntent().getExtras();
        day_name = extras.getString("day");
        ////

        mydb = new DataBaseHelper(this);
        configureView();
        //daryaft etelat vagti ke edit mikhad beshe
        bundle=getIntent().getExtras();


        Data();
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

        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInsert;

                isInsert = mydb.insertData(edt_title.getText().toString(), edt_text.getText().toString(),"1399 1 1","12 : 24",day_name);

                if(isInsert==true){
                        Toast.makeText(CreateNote.this,"The note was saved successfully" ,Toast.LENGTH_SHORT).show();
                }
                else{
                        Toast.makeText(CreateNote.this,"The note could not be saved successfully" ,Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(CreateNote.this,MainActivity.class);
                intent.putExtra("day", day_name);
                startActivity(intent);




            }
        });
        /////////////////////show notes recycler view in main activity/////////////////////

        btn_show_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateNote.this,MainActivity.class);
                startActivity(intent);
        }
        });

        ///////////////date time picker////////////

        btn_datetimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //save//
                boolean isInsert;

                isInsert = mydb.insertData(edt_title.getText().toString(), edt_text.getText().toString(),"1399 1 1","12 : 24",day_name);

                if(isInsert==true){
                    Toast.makeText(CreateNote.this,"The note was saved successfully" ,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CreateNote.this,"The note could not be saved successfully" ,Toast.LENGTH_SHORT).show();
                }

                //////////

                Intent intent=new Intent(CreateNote.this,setalarm.class);


                intent.putExtra("title", edt_title.getText().toString());
                intent.putExtra("text", edt_text.getText().toString());
                intent.putExtra("link",edt_link.getText().toString());
                intent.putExtra("day", day_name);

                startActivity(intent);

            }
        });
        /////////////////////////////////////////////
    }



    public void configureView(){
        edt_title=(EditText) findViewById(R.id.edt_title);
        edt_text=(EditText)findViewById(R.id.edt_text);
        edt_link=(EditText)findViewById(R.id.edt_link);

        btn_add_data=(Button)findViewById(R.id.btn_add_data);
        btn_show_notes=(Button)findViewById(R.id.btn_show_notes) ;
        btn_datetimepicker=(ImageButton) findViewById(R.id.datetimepicker);

        date=(TextView)findViewById(R.id.date);

        tClock = (TextView) findViewById(R.id.clock);
        uptext = (TextView) findViewById(R.id.uptext);
    }
    public String name_of_day(int i){
        String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        return days[i-1];
    }


}
