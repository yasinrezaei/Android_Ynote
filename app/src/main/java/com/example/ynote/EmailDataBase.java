package com.example.ynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ynote.DataBaseHelper.COL5;

public class EmailDataBase extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME="Email.db";
    public static  final String TABLE_NAME="Email_table";

    public static  final String COL1="ID";
    public static  final String COL2="EMAIL";
    public static  final String COL3="SUBJECT";
    public static  final String COL4="TEXT";
    public static  final String COL5="DAY";

    public EmailDataBase(Context context){

        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db=this.getWritableDatabase();

    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT,SUBJECT TEXT ,TEXT TEXT,DAY TEXT)");
    }
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData( String email, String subject,String text,String day){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL2,email);
        contentValues.put(COL3,subject);
        contentValues.put(COL4,text);
        contentValues.put(COL5,day);

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

    public boolean updateData(String id,String email, String subject,String text,String day){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(COL1,id);
        values.put(COL2,email);
        values.put(COL3,subject);
        values.put(COL4,text);
        values.put(COL5,day);


        long Result =db.update(TABLE_NAME,values,"ID=?",new String[]{id});
        if(Result==-1){
            return  false;
        }
        else {
            return true;
        }
    }

    public int deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }


}
