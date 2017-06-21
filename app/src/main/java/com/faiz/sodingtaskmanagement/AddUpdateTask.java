package com.faiz.sodingtaskmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.faiz.sodingtaskmanagement.Model.Task;
import com.faiz.sodingtaskmanagement.DB.TaskOperation;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddUpdateTask extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "com.faiz.id";
    private static final String EXTRA_ADD_UPDATE = "com.faiz.add_update";
    private EditText edit_text_name,edit_text_description;
    private Button button_add_update_task;
    private long taskId;
    private String mode;
    private Task newTask;
    private Task oldTask;
    private TaskOperation taskData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        newTask = new Task();
        oldTask = new Task();
        edit_text_name = (EditText) findViewById(R.id.edit_text_name);
        edit_text_description = (EditText) findViewById(R.id.edit_text_description);

        button_add_update_task = (Button) findViewById(R.id.button_add_update_task);
        taskData = new TaskOperation(this);

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if(mode.equals("Update")){
            taskData.open();
            button_add_update_task.setText("Update Task");
            taskId = getIntent().getLongExtra(EXTRA_TASK_ID, 0);
            initializeTask(taskId);
            taskData.close();
        }





        button_add_update_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskData.open();
                if(mode.equals("Add")){
                    newTask.setName(edit_text_name.getText().toString());
                    newTask.setDescription(edit_text_description.getText().toString());

                    newTask.setDateCreated(formatDate());
                    taskData.addTask(newTask);
                    taskData.close();
                    Toast t = Toast.makeText(AddUpdateTask.this, "Task " + newTask.getName() + " has been added", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateTask.this, MainActivity.class);
                    startActivity(i);
                } else {
                    oldTask.setName(edit_text_name.getText().toString());
                    oldTask.setDescription(edit_text_description.getText().toString());
                    oldTask.setDateUpdated(formatDate());
                    taskData.updateTask(oldTask);
                    taskData.close();
                    Toast t = Toast.makeText(AddUpdateTask.this, "Task " + oldTask.getName() + " has been updated", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateTask.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void initializeTask(long taskId) {
        oldTask = taskData.getTask(taskId);
        edit_text_name.setText(oldTask.getName());
        edit_text_description.setText(oldTask.getDescription());
    }


    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

}
