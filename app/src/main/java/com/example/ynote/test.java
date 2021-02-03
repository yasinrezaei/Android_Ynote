package com.example.ynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class test extends AppCompatActivity {
    Button createnote,createemail,createlocation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        confugureview();

        createnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(test.this,MainActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_right);
            }
        });

        createemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(test.this,EmailActivity.class);
                startActivity(intent);

            }
        });



        createlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





    }


    public void confugureview(){
        createnote=(Button)findViewById(R.id.note);
        createemail=(Button)findViewById(R.id.email);
        createlocation=(Button)findViewById(R.id.location);

    }
}
