package com.humanbooster.todolist.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 25/11/15.
 */
public class DatabaseService {

    private SQLiteDatabase db;

    public DatabaseService(DatabaseHelper helper){
        db = helper.getWritableDatabase();
    }

    public void addTask(Task task) {
        ContentValues newEntry = new ContentValues();
        newEntry.put("title", task.getTitle());
        newEntry.put("desc", task.getDesc());
        db.insert("task", null, newEntry);
    }

    public List<Task> getAllTask() {
        List<Task> allContact = new ArrayList<Task>();

        Cursor c = db.rawQuery("SELECT * FROM task;", null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Task contact = new Task(c.getInt(0), c.getString(1), c.getString(2));
            allContact.add(contact);
            c.moveToNext();
        }
        c.close();

        return allContact;
    }

    public void removeAll() {
        db.delete("task", null, null);
    }

    public void update(Task t) {
        ContentValues newEntry = new ContentValues();
        newEntry.put("title", t.getTitle());
        newEntry.put("desc", t.getDesc());
        db.update("task", newEntry, "_id = ?", new String[] {t.getId().toString()});
    }
}
