package edu.ewubd.mynotes2019160068;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String Database_name= "MyNotes";
    private static final int Version= 2;

    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("Create TABLE noteList (noteId INTEGER PRIMARY KEY AUTOINCREMENT,courseId varchar(50) UNIQUE,topic varchar(50) ,date varchar(20),note varchar(5000));");
            System.out.println("Table Created");
        }
        catch (Exception e){
            Toast.makeText(context,"Error: "+e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE noteList;");
        onCreate(db);
    }
    public Boolean insertNotes(String courseId, String topic, String date, String note){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseId", courseId);
        contentValues.put("topic", topic);
        contentValues.put("date", date);
        contentValues.put("note", note);
        long result = DB.insert("noteList",null,contentValues);
        if(result==-1) {
            Toast.makeText(context,"Duplicate Course Id",Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;
    }

    public ArrayList<NoteList> getProblems(){
        ArrayList<NoteList> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor getProblem = sqLiteDatabase.rawQuery("SELECT noteId, courseId, topic, date, note FROM noteList;",null);
        while(getProblem.moveToNext()){
            int noteId = getProblem.getInt(0);
            String courseId = getProblem.getString(1);
            String topic = getProblem.getString(2);
            String date = getProblem.getString(3);
            String note = getProblem.getString(4);

            NoteList allNotes = new NoteList(noteId, courseId, topic, date, note);
            arrayList.add(allNotes);
        }
        return arrayList;
    }
    public String[] getNote(int noteId){
        String noteArray[] = new String[4];
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor getNote = sqLiteDatabase.rawQuery("SELECT courseId, topic, date, note FROM noteList WHERE noteId="+noteId+";",null);
        while(getNote.moveToNext()) {
            noteArray[0] = getNote.getString(0);
            noteArray[1] = getNote.getString(1);
            noteArray[2] = getNote.getString(2);
            noteArray[3] = getNote.getString(3);
        }
        return noteArray;
    }
    public Boolean updateNote(int noteId, String courseId, String topic, String date, String note){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseId", courseId);
        contentValues.put("topic", topic);
        contentValues.put("date", date);
        contentValues.put("note", note);

        long result = DB.update("noteList",contentValues, "noteId = ?", new String[]{String.valueOf(noteId)});
        if(result==-1) return false;
        else return true;
    }

    public Boolean deleteNote(int noteId){
        SQLiteDatabase DB = this.getWritableDatabase();
        long result = DB.delete("noteList", "noteId = ?", new String[]{String.valueOf(noteId)});
        if(result==-1) return false;
        else {
            Toast.makeText(context,"Note Has Been Deleted Successfully",Toast.LENGTH_LONG).show();
            return true;
        }
    }

}
