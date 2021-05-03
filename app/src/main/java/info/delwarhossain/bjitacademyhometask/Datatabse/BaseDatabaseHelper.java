package info.delwarhossain.bjitacademyhometask.Datatabse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import info.delwarhossain.bjitacademyhometask.Model.NoteModel;

public class BaseDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "emi_financial_guide.db";
    private static final int DATABASE_VERSION = 1;

    /*
    * Listing Table Names
     */
    private static final String TABLE_IMPORTANT_NOTES = "important_notes";

    //Common Column Names
    private static final String KEY_ID = "id";

    //Important Notes Table Column
    private static final String NOTE_TITLE_COLUMN = "title";
    private static final String NOTE_DETAILS_COLUMN = "details";

    private SQLiteDatabase wDb;
    private SQLiteDatabase rDb;

    public BaseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        wDb = this.getWritableDatabase();
        rDb = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String importantNotesTable = "CREATE TABLE " + TABLE_IMPORTANT_NOTES +
                " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOTE_TITLE_COLUMN + " TEXT, " +
                NOTE_DETAILS_COLUMN + " TEXT);";
        sqLiteDatabase.execSQL(importantNotesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMPORTANT_NOTES);
        onCreate(sqLiteDatabase);
    }

    public long insertNote(NoteModel note){
        ContentValues  values = new ContentValues();
        values.put(NOTE_TITLE_COLUMN, note.getTitle());
        values.put(NOTE_DETAILS_COLUMN, note.getDetails());
        long id = wDb.insert(TABLE_IMPORTANT_NOTES, null, values);
        return  id;
    }

    public NoteModel getNoteById(long id){
        String query = "SELECT * FROM " + TABLE_IMPORTANT_NOTES + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = rDb.rawQuery(query, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        NoteModel note = new NoteModel();
        note.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndex(NOTE_TITLE_COLUMN)));
        note.setDetails(cursor.getString(cursor.getColumnIndex(NOTE_DETAILS_COLUMN)));
        return  note;
    }

    public ArrayList<NoteModel> getAllNotes(){
        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();

        String query = "SELECT * FROM " + TABLE_IMPORTANT_NOTES + " ORDER BY " + KEY_ID + " DESC";

        Cursor cursor = rDb.rawQuery(query, null);


        while (cursor.moveToNext()){
            NoteModel note = new NoteModel();
            note.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(NOTE_TITLE_COLUMN)));
            note.setDetails(cursor.getString(cursor.getColumnIndex(NOTE_DETAILS_COLUMN)));
            notes.add(note);
        }

        return notes;
    }

    public void deleteNote(long id){
        wDb.delete(TABLE_IMPORTANT_NOTES, KEY_ID + " = ?", new String[] {String.valueOf(id)});
    }
}
