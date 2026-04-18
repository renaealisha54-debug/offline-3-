// Inside ProjectDatabaseHelper.java

// Save only the notes field
public void saveNotes(String notes) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("notes", notes);
    // Assuming you have a current project ID, otherwise update the most recent entry
    db.update("projects", values, "id = (SELECT MAX(id) FROM projects)", null);
}

// Retrieve the notes
public String getNotes() {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT notes FROM projects ORDER BY id DESC LIMIT 1", null);
    if (cursor.moveToFirst()) {
        String notes = cursor.getString(0);
        cursor.close();
        return notes;
    }
    cursor.close();
    return "";
}
