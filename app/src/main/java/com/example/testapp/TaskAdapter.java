package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class TaskAdapter extends BaseAdapter{

    Context myContext;
    int myLayout;
    List<Task> arrayTask;
    View view;

    public TaskAdapter(Context myContext, int myLayout, List<Task> arrayTask) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayTask = arrayTask;
    }

    @Override
    public int getCount() {
        return arrayTask.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayTask.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayTask.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,null);


        TextView hoten = (TextView) convertView.findViewById(R.id.name);
        TextView muctieu = (TextView) convertView.findViewById(R.id.target);
        TextView dueDate = (TextView) convertView.findViewById(R.id.duedate);
        final TextView idTask = (TextView) convertView.findViewById(R.id.idTask);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        Button deleteButton = (Button) convertView.findViewById(R.id.buttonDelete);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        hoten.setText(arrayTask.get(position).getName());
        muctieu.setText(arrayTask.get(position).getTarget());
        dueDate.setText(dateFormat.format(arrayTask.get(position).getDueDate()));
        idTask.setText(arrayTask.get(position).getIdTask());
        checkBox.setChecked(arrayTask.get(position).isStatus());


        final View finalConvertView = convertView;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String searchIdTask = idTask.getText().toString();
//                for(int i=0;i<ModelTask.getInstance().getTaskList().size();i++)
//                {
//                    if(ModelTask.getInstance().getTaskList().get(i).getIdTask().equals(searchIdTask))
//                    {
//                        ModelTask.getInstance().getTaskList().remove(i);
//                    }
//                }
//                finalConvertView.setLayoutParams(new AbsListView.LayoutParams(-1,1));
//                finalConvertView.setVisibility(View.GONE);
//
//                notifyDataSetChanged();

            }
        });



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String searchIdTask = idTask.getText().toString();
                for(Task a : ModelTask.getInstance().getTaskList())
                {
                    if(a.getIdTask().equals(searchIdTask))
                    {
                        a.setStatus(isChecked);
                    }
                }
                buttonView.setChecked(arrayTask.get(position).isStatus());

                notifyDataSetChanged();
//                Date currentDate = new Date(System.currentTimeMillis());
//                for(Task a : ModelTask.getInstance().getWeekList(currentDate))
//                {
//                    if(a.getIdTask().equals(searchIdTask))
//                    {
//                        a.setStatus(isChecked);
//                        notifyDataSetChanged();
//                    }
//                }

            }



        });







        return convertView;
    }




}