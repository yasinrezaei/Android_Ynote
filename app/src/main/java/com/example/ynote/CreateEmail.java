package com.example.ynote;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEmail extends AppCompatActivity {
    EditText edt_email,edt_text,edt_subject;
    Button btn_add_mail,btn_show_mails;
    ImageButton btn_datetimepicker;
    TextView date,uptext;
    EmailDataBase mydb;
    Bundle bundle;
    TextView tClock;
    String day_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_email);

        ////
        Bundle extras = getIntent().getExtras();
        day_name = extras.getString("day");
        ////

        mydb = new EmailDataBase(this);

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

        btn_add_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInsert;

                isInsert = mydb.insertData(edt_email.getText().toString(), edt_subject.getText().toString(),edt_text.getText().toString(),day_name);

                if(isInsert==true){
                    Toast.makeText(CreateEmail.this,"The email was saved successfully" ,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CreateEmail.this,"The email could not be saved successfully" ,Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(CreateEmail.this,EmailActivity.class);
                intent.putExtra("day", day_name);
                startActivity(intent);




            }
        });
        /////////////////////show notes recycler view in main activity/////////////////////

        btn_show_mails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateEmail.this,EmailActivity.class);
                startActivity(intent);
            }
        });

        ///////////////date time picker////////////

        btn_datetimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.insertData(edt_email.getText().toString(), edt_subject.getText().toString(),edt_text.getText().toString(),day_name);


                Intent intent=new Intent(CreateEmail.this,setalarm.class);


                intent.putExtra("title", "Send Email To");
                intent.putExtra("text", edt_email.getText().toString());
                intent.putExtra("link","123");
                intent.putExtra("day", day_name);

                startActivity(intent);

            }
        });
        /////////////////////////////////////////////
    }



    public void configureView(){
        edt_email=(EditText) findViewById(R.id.edt_email);
        edt_text=(EditText)findViewById(R.id.edt_text);
        edt_subject=(EditText)findViewById(R.id.edt_subject);

        btn_add_mail=(Button)findViewById(R.id.btn_add_mail);
        btn_show_mails=(Button)findViewById(R.id.btn_show_mails) ;
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
