package com.faiz.sodingtaskmanagement.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.faiz.sodingtaskmanagement.Model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdulazf on 6/9/2017.
 */

public class TaskOperation {
    public static final String LOGTAG = "TASK_MGMT_SYS";
    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            TaskDBHandler.COLUMN_ID,
            TaskDBHandler.COLUMN_NAME,
            TaskDBHandler.COLUMN_DESCRIPTION,
            TaskDBHandler.COLUMN_DATE_CREATED,
            TaskDBHandler.COLUMN_DATE_UPDATED
    };

    public TaskOperation(Context context) {
        dbhandler = new TaskDBHandler(context);
    }

    public void open() {
        Log.i(LOGTAG, "Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close() {
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    public Task addTask(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDBHandler.COLUMN_NAME, task.getName());
        contentValues.put(TaskDBHandler.COLUMN_DESCRIPTION, task.getDescription());
        contentValues.put(TaskDBHandler.COLUMN_DATE_CREATED, task.getDateCreated());
        contentValues.put(TaskDBHandler.COLUMN_DATE_UPDATED, task.getDateUpdated());
        long insertId = database.insert(TaskDBHandler.TABLE_TASKS, null, contentValues);
        task.setId(insertId);
        return task;
    }

    //getting single Task
    public Task getTask(long id) {
        Cursor cursor = database.query(TaskDBHandler.TABLE_TASKS, allColumns, TaskDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Task task = new Task(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return task;
    }

    public List<Task> getAllTasks() {
        //Cursor cursor = database.query(EmployeeDBHandler.TABLE_EMPLOYEES,allColumns,null,null,null, null, null);
        Cursor cursor = database.query(TaskDBHandler.TABLE_TASKS, allColumns,null,null,null, null, null);
        List<Task> tasks = new ArrayList<>();
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Task task = new Task();
                task.setId(cursor.getLong(cursor.getColumnIndex(TaskDBHandler.COLUMN_ID)));
                task.setName(cursor.getString(cursor.getColumnIndex(TaskDBHandler.COLUMN_NAME)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(TaskDBHandler.COLUMN_DESCRIPTION)));
                task.setDateCreated(cursor.getString(cursor.getColumnIndex(TaskDBHandler.COLUMN_DATE_CREATED)));
                task.setDateUpdated(cursor.getString(cursor.getColumnIndex(TaskDBHandler.COLUMN_DATE_UPDATED)));
                tasks.add(task);
            }
        }

        return tasks;
    }

    //updating task
    public int updateTask(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDBHandler.COLUMN_NAME, task.getName());
        contentValues.put(TaskDBHandler.COLUMN_DESCRIPTION, task.getDescription());
        contentValues.put(TaskDBHandler.COLUMN_DATE_CREATED, task.getDateCreated());
        contentValues.put(TaskDBHandler.COLUMN_DATE_UPDATED, task.getDateUpdated());

        //updating row
        return database.update(TaskDBHandler.TABLE_TASKS, contentValues, TaskDBHandler.COLUMN_ID + "=?",new String[]{ String.valueOf(task.getId())});
    }

    public void removeTask(Task task) {
        database.delete(TaskDBHandler.TABLE_TASKS, TaskDBHandler.COLUMN_ID + "=" + task.getId(), null);
    }
}
