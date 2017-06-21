package com.faiz.sodingtaskmanagement;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.faiz.sodingtaskmanagement.DB.TaskOperation;

public class MainActivity extends AppCompatActivity {

    private Button createTaskBtn, editTaskBtn, deleteTaskBtn, listAllTaskBtn;
    private TaskOperation taskOps;
    private static final String EXTRA_TASK_ID = "com.faiz.id";
    private static final String EXTRA_ADD_UPDATE = "com.faiz.add_update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTaskBtn = (Button) findViewById(R.id.create_task_btn);
        editTaskBtn = (Button) findViewById(R.id.edit_task_btn);
        deleteTaskBtn = (Button) findViewById(R.id.delete_task_btn);
        listAllTaskBtn = (Button) findViewById(R.id.list_all_task_btn);


        createTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddUpdateTask.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });

        editTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTaskIdAndUpdateTask();
            }
        });

        deleteTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTaskIdAndRemoveTask();
            }
        });

        listAllTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewAllTasks.class);
                startActivity(i);
            }
        });
    }

    public void getTaskIdAndUpdateTask() {
        LayoutInflater li = LayoutInflater.from(this);
        View getTaskIdView = li.inflate(R.layout.dialog_get_task_id, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(getTaskIdView);
        final EditText userInput = (EditText) getTaskIdView.findViewById(R.id.editTextDialogUserInput);
        //set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton(R.string.update_task_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity.this,AddUpdateTask.class);
                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
                        i.putExtra(EXTRA_TASK_ID, Long.parseLong(userInput.getText().toString()));
                        startActivity(i);
                    }
                })
                .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    } }).create()
                .show();
    }

    public void getTaskIdAndRemoveTask() {
        LayoutInflater li = LayoutInflater.from(this);
        View getTaskIdView = li.inflate(R.layout.dialog_get_task_id, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(getTaskIdView);

        final EditText userInput = (EditText) getTaskIdView.findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton(R.string.delete_task_btn,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // get user input and set it to result
                        // edit text
                        taskOps = new TaskOperation(MainActivity.this);
                        taskOps.open();
                        taskOps.removeTask(taskOps.getTask(Long.parseLong(userInput.getText().toString())));
                        taskOps.close();
                        Toast t = Toast.makeText(MainActivity.this,R.string.task_remove_toast_message,Toast.LENGTH_SHORT);
                        t.show();
                    }
                })
                .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    } }).create()
                .show();
    }
}
