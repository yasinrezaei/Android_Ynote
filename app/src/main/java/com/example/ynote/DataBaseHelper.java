package com.example.ynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME="Note.db";
    public static  final String TABLE_NAME="Note_table";

    public static  final String COL1="ID";
    public static  final String COL2="TITLE";
    public static  final String COL3="TEXT";
    public static  final String COL4="DATE";
    public static  final String COL5="TIME";
    public static  final String COL6="DAY";
    //public static  final String COL7="LINK";

    public DataBaseHelper(Context context){

        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db=this.getWritableDatabase();

    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,TEXT TEXT ,DATE TEXT,TIME TEXT,DAY TEXT)");
    }


    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData( String title, String text,String date,String time,String day){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,title);
        contentValues.put(COL3,text);
        contentValues.put(COL4,date);
        contentValues.put(COL5,time);
        contentValues.put(COL6,day);
        //contentValues.put(COL7,link);

        long Result=db.insert(TABLE_NAME,null,contentValues);
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME ,null);
        return res;
    }

    ////
    public boolean updateData(String id,String title,String text,String date,String time,String day){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL1,id);
        values.put(COL2,title);
        values.put(COL3,text);
        values.put(COL4,date);
        values.put(COL5,time);
        values.put(COL6,day);

        long Result =db.update(TABLE_NAME,values,"ID=?",new String[]{id});
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }


    ///

    public int deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

}
