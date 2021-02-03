package com.example.ynote;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    DataBaseHelper mydb;
    List<DataInfo> dataInfos;

    Integer deleteRow;

    public RecyclerAdapter(Context context, List<DataInfo> dataInfos) {
        this.context = context;
        this.dataInfos = dataInfos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataInfo dataInfo=dataInfos.get(position);
        holder.title.setText(  "TITLE :   " + dataInfo.title);
        holder.text.setText(  "TEXT :    " + dataInfo.text);
        holder.day.setText(dataInfo.day);



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to delete this note?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                mydb=new DataBaseHelper(context);
                                deleteRow=mydb.deleteData(dataInfo.id);
                                dataInfos.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, dataInfos.size());
                                dialog.dismiss();
                                Toast.makeText(context,"The note was successfully deleted" ,Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        //edit dar list
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditNote.class);
                intent.putExtra("idKey",dataInfo.id);
                intent.putExtra("titleKey",dataInfo.title);
                //intent.putExtra("linkkey",dataInfo.link);
                intent.putExtra("textKey",dataInfo.text);
                intent.putExtra("dateKey",dataInfo.date);
                intent.putExtra("timeKey",dataInfo.title);
                intent.putExtra("daykey",dataInfo.day);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,text,date,time,day,link;
        Button edit,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.show_title);
            text=(TextView)itemView.findViewById(R.id.show_text);
            //link=(TextView)itemView.findViewById(R.id.show_link);
            day=(TextView)itemView.findViewById(R.id.showday);

            delete=(Button)itemView.findViewById(R.id.item_delete);
            edit=(Button)itemView.findViewById(R.id.item_edit);
        }
    }


}
