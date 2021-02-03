package com.example.ynote;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class EmailFragment extends Fragment {
    RecyclerView recyclerView;
    List<EmailData> emailDataList=new ArrayList<>();
    String name_of_day;
    EmailDataBase mydb;
    EmailRecyclerAdapter adapter;

    ImageButton btn_add_mail,backtomainو;
    Calendar calendar=Calendar.getInstance();
    private static final int CHANNEL_ID = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mail_fragment,container,false);
        configureView(view);
        Bundle bundle=getArguments();
        name_of_day=bundle.getString("day");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ///add note

        btn_add_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CreateEmail.class);
                intent.putExtra("day", name_of_day);
                startActivity(intent);
            }
        });


        mydb=new EmailDataBase(getContext());

        ////
        Cursor res=mydb.getAllData();
        if(res.getCount()==0){
            Toast.makeText(getContext(),"There is nothing to show" ,Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()){
            EmailData emailData=new EmailData();

            emailData.id=res.getString(0);
            emailData.email=res.getString(1);
            emailData.subject=res.getString(2);
            emailData.text=res.getString(3);
            emailData.day=res.getString(4);
            emailDataList.add(emailData);

        }


        List<EmailData> filteredList = new ArrayList<>();
        for (EmailData data:emailDataList){
            if(data.day.contains(name_of_day)){
                filteredList.add(data);
            }
        }
        Collections.reverse(filteredList);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new EmailRecyclerAdapter(getContext(),filteredList);
        recyclerView.setAdapter(adapter);
    }



    public void configureView(View view){
        btn_add_mail=(ImageButton)view.findViewById(R.id.btn_create_note);


    }

}
