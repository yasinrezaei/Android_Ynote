package com.example.ynote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmailRecyclerAdapter extends RecyclerView.Adapter<EmailRecyclerAdapter.ViewHolder> {

    Context context;
    DataBaseHelper mydb;
    List<EmailData> emailDatas;

    Integer deleteRow;

    public EmailRecyclerAdapter(Context context, List<EmailData> emailDatas) {
        this.context = context;
        this.emailDatas = emailDatas;
    }

    @NonNull
    @Override
    public EmailRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.email_recycler_item,parent,false);
        return new EmailRecyclerAdapter.ViewHolder(view);
    }



    public void onBindViewHolder(@NonNull EmailRecyclerAdapter.ViewHolder holder, int position) {
        final EmailData emailData=emailDatas.get(position);
        holder.email.setText(  "EMAIL :   " + emailData.email);
        holder.subject.setText(  "SUBJECT :    " + emailData.subject);
        holder.text.setText("TEXT :    "+emailData.text);
        holder.day.setText(emailData.day);



        //send
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SendEmail.class);
                intent.putExtra("subjectkey",emailData.subject);
                intent.putExtra("textkey",emailData.text);
                intent.putExtra("ekey",emailData.email);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emailDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView email,text,subject,day;
        Button send;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            email=(TextView)itemView.findViewById(R.id.show_mail);
            text=(TextView)itemView.findViewById(R.id.show_text);
            subject=(TextView)itemView.findViewById(R.id.show_subject);
            day=(TextView)itemView.findViewById(R.id.showday);

            send=(Button)itemView.findViewById(R.id.item_send);

        }
    }
}
