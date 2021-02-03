package com.example.ynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity implements Animation.AnimationListener {
    Button start;
    TextView txt1;
    Animation button_shake_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        txt1=(TextView)findViewById(R.id.txt1);


        button_shake_anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        button_shake_anim.setAnimationListener(this);


        start=findViewById(R.id.btn_start);
        start.setAnimation(button_shake_anim);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartActivity.this,test.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
