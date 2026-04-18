package com.darvin.codeedit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProjectDatabaseHelper extends SQLiteOpenHelper {
    public ProjectDatabaseHelper(Context context) {
        super(context, "OfflineProjects.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE projects (id INTEGER PRIMARY KEY, title TEXT, type TEXT, notes TEXT, code TEXT)");
    }

    public void saveProject(String title, String type, String notes, String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("type", type);
        values.put("notes", notes);
        values.put("code", code);
        db.insert("projects", null, values);
    }
}
