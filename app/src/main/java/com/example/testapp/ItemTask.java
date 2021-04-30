package com.example.testapp;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemTask extends AppCompatActivity {
    private CheckBox checkBox;
    private TextView textViewName, textViewTarget, textViewDueDate, textViewIdTask;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_task_view);
        checkBox = findViewById(R.id.checkBox);

        textViewName = findViewById(R.id.name);
        textViewTarget = findViewById(R.id.target);
        textViewDueDate = findViewById(R.id.duedate);
        textViewIdTask = findViewById(R.id.idTask);



    }

}
