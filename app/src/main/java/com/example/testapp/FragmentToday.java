package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class FragmentToday extends Fragment
{
    ListView listViewToday;
    View view;
    ArrayList<Task> dayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_today, container, false);

        Date currentDate = new Date(System.currentTimeMillis());
        listViewToday = view.findViewById(R.id.list_view_today);

        final ArrayList<Task> dayList = ModelTask.getInstance().getDayList(ModelTask.getInstance().getChoosedDate());

        TaskAdapter adapter = new TaskAdapter(
                getActivity(),
                R.layout.item_task_view,
                dayList
        );



        listViewToday.setAdapter(adapter);
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        if(listViewToday!=null)
        {
            listViewToday = view.findViewById(R.id.list_view_today);
            ArrayList<Task> dayList = ModelTask.getInstance().getDayList(ModelTask.getInstance().getChoosedDate());

            TaskAdapter adapter = new TaskAdapter(
                    getActivity(),
                    R.layout.item_task_view,
                    dayList
            );



            listViewToday.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }

    }


}