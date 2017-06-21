# SodingTaskManagement
This app is basic TodoList app that build for Android.
Problem Statement

With a lot of routine and daily work and task, it is hard for the user to keep track their task and might miss out some important task.

Objective
Provide a platform that are mobile and portable for user to track their task effectively


How it works

This application is build using Android studio 2.2.1, which the minimum target of the operating system is Android Jelly Bean 4.3.3.

I create 2 package under com.faiz.sodingtaskmanagement:

1.	DB
2.	Model

Under the DB package, I create two classes. First one, is TaskDBHandler classes which handle the task for creation database using SQLITE.

The database that I used for this application is tasks.db, I create Tasks table which contain below field:
1.	Id – autoincrement id to make sure every task created is unique
2.	Name – used to store the task name
3.	Description – used to store the description of the task
4.	dateCreated – date of task created
5.	dateUpdate – date of task updated

The second one is TaskOperation, which contain all the method that used for doing CRUD operation on the SQLITE database.

One the TaskOperation constructor, I instantiate the TaskDBHandler classes.

In this class, it give the method like below:
1.	open – open database connection
2.	close – database connection
3.	addTask – to insert operation on Tasks table.
4.	getTask – take 1 parameter, which is id. Which will be use to find the task information that match with the id.
5.	getAllTask – select all task that have been created inside Task table.
6.	updateTask – take Task object as parameter, to update information about the task.
7.	RemoveTask – take task object as parameter, then do select by using getTask method to find the right task id for deletion.
8.	
In the Model package, I create 3 classes:

1.	AddUpdateTask – to handle the “ADD” and “UPDATE” operation on this mobile application.
2.	MainActivity – act as the home screen of this app, which will show menu for the Task operation like:
a.	CREATE TASK
b.	EDIT TASK
c.	DELETE TASK
d.	LIST ALL TASK
3.	ViewAllTask – to show all the task list that have been created by the user.

All these classes, handle the interaction from the screen/ layout that build using the XML and then connect with the TaskOperation class to do the CRUD operation on the SQLITE database.

I build 4 layout for this application, which are:
1.	activity_add_task.xml
2.	activity_main.xml
3.	activity_view_all_tasks.xml
4.	dialog_get_task_id.xml


activity_add_task.xml – layout that are used to add task into the app. 2 view element that are used inside this layout:

1.	EditText – to take the user input for the task name and description
2.	Button – to create the task based on the user input on EditText

activity_main.xml – layout for the main screen, which will show menu for Task Management mobile apps.

It use 2 view element also:
1.	TextView – show the title on the menu
2.	Button – to handle create task, edit task, delete task, update task

activity_view_all_tasks.xml – layout used to show all the list of task that are created on this mobile apps.

It is use 2 view element:

1.	TextView – I created it to show message when no task have been created. The default of this elemet is invisble.
2.	ListView – store all the task information inside it

dialog_get_task_id.xml – build to take user input for update and delete the task. It will take the id as the input. It will pop up, when we pressed button Edit Task and Delete task.

The layout consist of two element:
1.	TextView – show the title of dialog menu which is “enter task id”
2.	EditText – to take user input for the task id they want to update/delete

This layout is attach to the DialogBuilder as the layout for the popup message.




