package com.example.testapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText editTextName,editTextTarget,editTextDueDate;
    private Button button, buttonChooseDay, buttonDelete;
    private TextView textViewName,textViewIdTask;
    private DatePicker datePicker = new DatePicker();
    private Date DueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTextName=findViewById(R.id.editTextName);
        editTextTarget=findViewById(R.id.editTextTarget);
        editTextDueDate=findViewById(R.id.editTextDueDate);

        button = findViewById(R.id.buttonOke);
        buttonChooseDay = findViewById(R.id.buttonChooseDay);

        textViewName = findViewById(R.id.textViewName);

        datePicker = new DatePicker();

        editTextDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker.show(getSupportFragmentManager(),"date picker");

            }

        });
        buttonChooseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker.show(getSupportFragmentManager(),"date picker");

            }

        });





        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        editTextTarget.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        editTextDueDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editTextName.getText().toString().equals("") || editTextDueDate.getText().toString().equals("") || editTextTarget.getText().toString().equals(""))
                {

                        AlertDialog.Builder builder = new AlertDialog.Builder(TaskDetail.this);
                        builder.setTitle("Warning");
                        builder.setMessage("Enter your task please");

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                }
                else
                {
                    Task newTask = new Task();
                    newTask.setName(editTextName.getText().toString());
                    newTask.setTarget(editTextTarget.getText().toString());
                    Date currentDate = new Date(System.currentTimeMillis());

                    newTask.setDueDate(DueDate);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
                    newTask.setIdTask( String.valueOf(newTask.getId()) +"_"+ dateFormat.format(DueDate));
                    boolean check=false;
                    for(Task x : ModelTask.getInstance().getTaskList())
                    {
                        if(x.getName().equals(newTask.getName()) && x.getTarget().equals(newTask.getTarget()) && x.getDueDate().equals(newTask.getDueDate()))
                        {
                            check=true;
                            AlertDialog.Builder builder = new AlertDialog.Builder(TaskDetail.this);
                            builder.setTitle("Warning");
                            builder.setMessage("Your task already exists!");
                            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Task newTask = new Task();
                                    newTask.setName(editTextName.getText().toString());
                                    newTask.setTarget(editTextTarget.getText().toString());
                                    Date currentDate = new Date(System.currentTimeMillis());

                                    newTask.setDueDate(DueDate);
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
                                    newTask.setIdTask( String.valueOf(newTask.getId()) +"_"+ dateFormat.format(DueDate));
                                    ModelTask.getInstance().getTaskList().add(newTask);
                                }


                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                            break;
                        }

                    }
                    if(check==false) {

                        ModelTask.getInstance().getTaskList().add(newTask);
                        Toast.makeText(TaskDetail.this, "  done", Toast.LENGTH_SHORT).show();
                    }

                    ModelTask.getInstance().setChoosedDate(currentDate);
                }

            }



        });


    }


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        DueDate = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String s = dateFormat.format(DueDate);
        editTextDueDate.setText(s);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}