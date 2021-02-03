package com.example.ynote;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class NoteFragment extends Fragment {
    RecyclerView recyclerView;
    List<DataInfo> dataInfosList=new ArrayList<>();
    String name_of_day;
    DataBaseHelper mydb;
    RecyclerAdapter adapter;

    ImageButton btn_add_note,back;
    Calendar calendar=Calendar.getInstance();
    private static final int CHANNEL_ID = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_fragment,container,false);
        configureView(view);
        Bundle bundle=getArguments();
        name_of_day=bundle.getString("day");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ///add note

        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CreateNote.class);
                intent.putExtra("day", name_of_day);
                startActivity(intent);
            }
        });

       // back.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View view) {
            //    Intent intent=new Intent(getActivity(),FirstActivity.class);
            //    startActivity(intent);
            //}
        //});


        mydb=new DataBaseHelper(getContext());

        ////
        Cursor res=mydb.getAllData();
        if(res.getCount()==0){
            Toast.makeText(getContext(),"There is nothing to show" ,Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()){
            DataInfo dataInfo=new DataInfo();

            dataInfo.id=res.getString(0);
            dataInfo.title=res.getString(1);
            dataInfo.text=res.getString(2);
            dataInfo.date=res.getString(3);
            dataInfo.time=res.getString(4);
            dataInfo.day=res.getString(5);
            dataInfosList.add(dataInfo);

        }


        List<DataInfo> filteredList = new ArrayList<>();
        for (DataInfo data:dataInfosList){
            if(data.day.contains(name_of_day)){
               filteredList.add(data);
            }
        }
        Collections.reverse(filteredList);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new RecyclerAdapter(getContext(),filteredList);
        recyclerView.setAdapter(adapter);
    }





    public void configureView(View view){
        btn_add_note=(ImageButton)view.findViewById(R.id.btn_create_note);


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createnotificationchanel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.BASE){
            CharSequence name="ynotechannel";
            String description="to show note alarms notif";
            int importamce= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("A",name,importamce);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
