package com.example.testapp;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class FragmentWeek extends Fragment
{
    ListView listViewWeek;
    ArrayList<Task> weekList;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
         view = inflater.inflate(R.layout.fragment_week, container, false);
        Date currentDate = new Date(System.currentTimeMillis());
        listViewWeek = view.findViewById(R.id.list_view_week);
        listViewWeek.setItemsCanFocus(false);

        weekList= ModelTask.getInstance().getWeekList(ModelTask.getInstance().getChoosedDate());
        final TaskAdapter adapter = new TaskAdapter(
                getActivity(),
                R.layout.item_task_view,
                weekList
        );
        listViewWeek.setAdapter(adapter);


        listViewWeek.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("lll", "onItemLongClick: " );
                weekList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        listViewWeek = view.findViewById(R.id.list_view_week);
        ArrayList<Task> weekList = ModelTask.getInstance().getWeekList(ModelTask.getInstance().getChoosedDate());
        TaskAdapter adapter = new TaskAdapter(
                getActivity(),
                R.layout.item_task_view,
                weekList
        );

        listViewWeek.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


}

//interface updateTask
//{
//    void setUpDate();
//}