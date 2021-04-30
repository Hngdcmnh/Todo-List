package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class FragmentMonth extends Fragment
{
    ListView listViewMonth;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_month, container, false);
        Date currentDate = new Date(System.currentTimeMillis());
        listViewMonth = view.findViewById(R.id.list_view_month);

        final ArrayList<Task> monthList = ModelTask.getInstance().getMonthList(ModelTask.getInstance().getChoosedDate());
        final TaskAdapter adapter = new TaskAdapter(
                getActivity(),
                R.layout.item_task_view,
                monthList
        );



        listViewMonth.setAdapter(adapter);
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        if (listViewMonth != null) {
            listViewMonth = view.findViewById(R.id.list_view_month);
            ArrayList<Task> monthList = ModelTask.getInstance().getMonthList(ModelTask.getInstance().getChoosedDate());
            TaskAdapter adapter = new TaskAdapter(
                    getActivity(),
                    R.layout.item_task_view,
                    monthList
            );

            listViewMonth.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }
}