package com.example.ynote;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EmailActivity extends AppCompatActivity {

    Button btn_sat,btn_sun,btn_mon,btn_tue, btn_wed,btn_thu,btn_fri;
    String name_of_day="saturday";
    ImageButton back_main_email;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name_of_day = extras.getString("day");
        }

        configureView();

        ///Email fragment
        EmailFragment frag = new EmailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("day", name_of_day );
        frag.setArguments(bundle);
        fragmentTransaction.add(R.id.main_frame, frag);
        fragmentTransaction.commit();
        ////

        back_main_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EmailActivity.this,test.class);
                startActivity(intent);
            }
        });

        btn_sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///color change////
                btn_sat.setTextColor(Color.BLUE);
                btn_sun.setTextColor(Color.BLACK);
                btn_mon.setTextColor(Color.BLACK);
                btn_tue.setTextColor(Color.BLACK);
                btn_wed.setTextColor(Color.BLACK);
                btn_thu.setTextColor(Color.BLACK);
                btn_fri.setTextColor(Color.BLACK);

                //////////
                name_of_day="saturday";

                ///note fragment
                EmailFragment frag_sat = new EmailFragment();
                FragmentManager fragmentManager_2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_2 = fragmentManager_2.beginTransaction();
                Bundle bundle_sat = new Bundle();
                bundle_sat.putString("day", name_of_day );
                frag_sat.setArguments(bundle_sat);
                fragmentTransaction_2.add(R.id.main_frame, frag_sat);
                fragmentTransaction_2.commit();
                ////



            }
        });
        btn_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sat.setTextColor(Color.BLACK);
                btn_sun.setTextColor(Color.BLUE);
                btn_mon.setTextColor(Color.BLACK);
                btn_tue.setTextColor(Color.BLACK);
                btn_wed.setTextColor(Color.BLACK);
                btn_thu.setTextColor(Color.BLACK);
                btn_fri.setTextColor(Color.BLACK);

                name_of_day="sunday";

                ///note fragment
                EmailFragment frag_sun = new EmailFragment();
                FragmentManager fragmentManager_3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_3 = fragmentManager_3.beginTransaction();
                Bundle bundle_sun = new Bundle();
                bundle_sun.putString("day", name_of_day );
                frag_sun.setArguments(bundle_sun);
                fragmentTransaction_3.add(R.id.main_frame, frag_sun);
                fragmentTransaction_3.commit();
                ////


            }
        });
        btn_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sat.setTextColor(Color.BLACK);
                btn_sun.setTextColor(Color.BLACK);
                btn_mon.setTextColor(Color.BLUE);
                btn_tue.setTextColor(Color.BLACK);
                btn_wed.setTextColor(Color.BLACK);
                btn_thu.setTextColor(Color.BLACK);
                btn_fri.setTextColor(Color.BLACK);

                name_of_day="monday";
                ///note fragment
                EmailFragment frag_mon = new EmailFragment();
                FragmentManager fragmentManager_4 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_4 = fragmentManager_4.beginTransaction();
                Bundle bundle_mon = new Bundle();
                bundle_mon.putString("day", name_of_day );
                frag_mon.setArguments(bundle_mon);
                fragmentTransaction_4.add(R.id.main_frame, frag_mon);
                fragmentTransaction_4.commit();
                ////


            }
        });
        btn_tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sat.setTextColor(Color.BLACK);
                btn_sun.setTextColor(Color.BLACK);
                btn_mon.setTextColor(Color.BLACK);
                btn_tue.setTextColor(Color.BLUE);
                btn_wed.setTextColor(Color.BLACK);
                btn_thu.setTextColor(Color.BLACK);
                btn_fri.setTextColor(Color.BLACK);

                name_of_day="tuesday";
                ///note fragment
                EmailFragment frag_tue = new EmailFragment();
                FragmentManager fragmentManager_5 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_5 = fragmentManager_5.beginTransaction();
                Bundle bundle_tue = new Bundle();
                bundle_tue.putString("day", name_of_day );
                frag_tue.setArguments(bundle_tue);
                fragmentTransaction_5.add(R.id.main_frame, frag_tue);
                fragmentTransaction_5.commit();
                ////


            }
        });
        btn_wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sat.setTextColor(Color.BLACK);
                btn_sun.setTextColor(Color.BLACK);
                btn_mon.setTextColor(Color.BLACK);
                btn_tue.setTextColor(Color.BLACK);
                btn_wed.setTextColor(Color.BLUE);
                btn_thu.setTextColor(Color.BLACK);
                btn_fri.setTextColor(Color.BLACK);

                name_of_day="wednesday";
                ///note fragment
                EmailFragment frag_wed = new EmailFragment();
                FragmentManager fragmentManager_6 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_6 = fragmentManager_6.beginTransaction();
                Bundle bundle_wed = new Bundle();
                bundle_wed.putString("day", name_of_day );
                frag_wed.setArguments(bundle_wed);
                fragmentTransaction_6.add(R.id.main_frame, frag_wed);
                fragmentTransaction_6.commit();
                ////


            }
        });
        btn_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sat.setTextColor(Color.BLACK);
                btn_sun.setTextColor(Color.BLACK);
                btn_mon.setTextColor(Color.BLACK);
                btn_tue.setTextColor(Color.BLACK);
                btn_wed.setTextColor(Color.BLACK);
                btn_thu.setTextColor(Color.BLUE);
                btn_fri.setTextColor(Color.BLACK);

                name_of_day="thursday";

                ///note fragment
                EmailFragment frag_thu = new EmailFragment();
                FragmentManager fragmentManager_7 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_7 = fragmentManager_7.beginTransaction();
                Bundle bundle_thu = new Bundle();
                bundle_thu.putString("day", name_of_day );
                frag_thu.setArguments(bundle_thu);
                fragmentTransaction_7.add(R.id.main_frame, frag_thu);
                fragmentTransaction_7.commit();
                ////


            }
        });
        btn_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_sat.setTextColor(Color.BLACK);
                btn_sun.setTextColor(Color.BLACK);
                btn_mon.setTextColor(Color.BLACK);
                btn_tue.setTextColor(Color.BLACK);
                btn_wed.setTextColor(Color.BLACK);
                btn_thu.setTextColor(Color.BLACK);
                btn_fri.setTextColor(Color.BLUE);

                name_of_day="friday";

                ///note fragment
                EmailFragment frag_fri = new EmailFragment();
                FragmentManager fragmentManager_8 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction_8 = fragmentManager_8.beginTransaction();
                Bundle bundle_fri = new Bundle();
                bundle_fri.putString("day", name_of_day );
                frag_fri.setArguments(bundle_fri);
                fragmentTransaction_8.add(R.id.main_frame, frag_fri);
                fragmentTransaction_8.commit();
                ////


            }
        });







    }

    public void configureView(){
        btn_sat=(Button)findViewById(R.id.saturday);
        btn_sun=(Button)findViewById(R.id.sunday);
        btn_mon=(Button)findViewById(R.id.monday);
        btn_tue=(Button)findViewById(R.id.tuesday);
        btn_wed=(Button)findViewById(R.id.wednesday);
        btn_thu=(Button)findViewById(R.id.thursday);
        btn_fri=(Button)findViewById(R.id.friday);

        back_main_email=(ImageButton)findViewById(R.id.back_email);

    }
}
