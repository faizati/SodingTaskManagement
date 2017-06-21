package com.faiz.sodingtaskmanagement;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.faiz.sodingtaskmanagement.Model.Task;
import com.faiz.sodingtaskmanagement.DB.TaskOperation;

import java.util.List;

import static android.R.id.list;

public class ViewAllTasks extends AppCompatActivity {
    private TaskOperation taskOperation;
    List<Task> tasks;
    private ListView lv;
    private TextView message_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_tasks);
        taskOperation = new TaskOperation(this);
        taskOperation.open();
        tasks = taskOperation.getAllTasks();
        taskOperation.close();
        lv = (ListView) findViewById(android.R.id.list);
        if(tasks.isEmpty()) {
            message_id = (TextView) findViewById(R.id.message_id);
            message_id.setText("No task is created yet, please create one");
            message_id.setVisibility(View.VISIBLE);
        }else {
            ArrayAdapter<Task> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, tasks);
            lv.setAdapter(adapter);
        }

    }

}
