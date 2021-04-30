package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UpdateTask  {
//    private TextView content;
//    private RecyclerView rvList;
//    private List<Task> taskList;
//    private TaskAdapter taskAdapter;
//    private LinearLayoutManager linearLayoutManager;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager;

    private BottomNavigationView mNavigationView;
    private ViewPager mViewPager;
    private RecyclerView dayBar;
    private DayBarItemAdapter dayBarItemAdapter;
    private List<DayBarItem> DayBarItemList = new ArrayList<>(7);

    private FloatingActionButton Add;

        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            SetDayBar();

            Add=findViewById(R.id.todo_add_button);



            mNavigationView = findViewById(R.id.bottom_navigation);
            mViewPager = findViewById(R.id.view_pager);

            //RecyclerView
            dayBar = findViewById(R.id.recyclerViewDayBar);
            dayBarItemAdapter = new DayBarItemAdapter(this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
            dayBar.setLayoutManager(linearLayoutManager);

            dayBarItemAdapter.setData(getListItem());
            dayBar.setAdapter(dayBarItemAdapter);


            //Get day
            dayBarItemAdapter.setmDayBarListener(new DayBarItemAdapter.OnDayBarClickListener() {
                @Override
                public void onDayBarClick(DayBarItem choosedItem) {
//                    Toast.makeText( MainActivity.this,"onDayBarClick: "+position,Toast.LENGTH_SHORT ).show();
//                    Date currentDate = new Date(System.currentTimeMillis());
//                    Toast.makeText(MainActivity.this,"onDayBarClick: "+position,Toast.LENGTH_LONG).show();
//                    Log.e("dd", "onDayBarClick: " +currentDate );
                    ModelTask.getInstance().setChoosedDate(choosedItem.getDayItem());
                    setmViewPager();

                }
            });

            // update ListTask
            Date currentDate = new Date(System.currentTimeMillis());
            ModelTask.getInstance().getTaskList().add(new Task("Fix bug Java app","3 hours", currentDate));
            ModelTask.getInstance().getTaskList().add(new Task("Run","5 km", currentDate));
            setUpViewPager();

            // update bottomnavigation


            mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.btnvtoday:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.btnvweek:
                            mViewPager.setCurrentItem(1);
                            break;
                        case R.id.btnvmonth:
                            mViewPager.setCurrentItem(2);
                            break;
                    }
                    return true;
                }
            });

            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addTask();
                }
            });

        }

        private List<DayBarItem> getListItem()
        {

            return DayBarItemList;
        }


        private void setmViewPager()
        {
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mViewPager.setAdapter(viewPagerAdapter);
            mViewPager.setCurrentItem(0);
            mNavigationView.getMenu().findItem(R.id.btnvtoday).setChecked(true);
        }

        private void setUpViewPager()
        {
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mViewPager.setAdapter(viewPagerAdapter);

            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position)
                    {
                        case 0:
                            mNavigationView.getMenu().findItem(R.id.btnvtoday).setChecked(true);

                            break;
                        case 1:
                            mNavigationView.getMenu().findItem(R.id.btnvweek).setChecked(true);

                            break;
                        case 2:
                            mNavigationView.getMenu().findItem(R.id.btnvmonth).setChecked(true);

                            break;
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }

    public void addTask() {
        Intent intent = new Intent(this, TaskDetail.class);
        startActivity(intent);
    }

    void SetDayBar()
    {
        DayBarItemList.add(new DayBarItem());
        DayBarItemList.add(new DayBarItem());
        DayBarItemList.add(new DayBarItem());
        DayBarItemList.add(new DayBarItem());
        DayBarItemList.add(new DayBarItem());
        DayBarItemList.add(new DayBarItem());
        DayBarItemList.add(new DayBarItem());

        Calendar calendar1 = Calendar.getInstance();
        Date currentDate = calendar1.getTime();
        Calendar calendar2 = Calendar.getInstance();
//        Date currentDate = new Date(System.currentTimeMillis());
        String day = currentDate.toString().substring(0,3);
        String dayNum = currentDate.toString().substring(8,10);
        int idCurrentDate=0;
        try {
            switch (currentDate.toString().substring(0, 3)) {
                case "Mon": {
                    DayBarItemList.get(0).setDayItem(currentDate);
                    idCurrentDate = 0;
                }
                break;
                case "Tue": {
                    DayBarItemList.get(1).setDayItem(currentDate);
                    idCurrentDate = 1;
                }
                break;
                case "Wed": {
                    DayBarItemList.get(2).setDayItem(currentDate);
                    idCurrentDate = 2;
                }
                break;
                case "Thu": {
                    DayBarItemList.get(3).setDayItem(currentDate);
                    idCurrentDate = 3;
                }
                break;
                case "Fri": {
                    DayBarItemList.get(4).setDayItem(currentDate);
                    idCurrentDate = 4;
                }
                break;
                case "Sat": {
                    DayBarItemList.get(5).setDayItem(currentDate);
                    idCurrentDate = 5;
                }
                break;
                case "Sun": {
                    DayBarItemList.get(6).setDayItem(currentDate);
                    idCurrentDate = 6;
                }
                break;
                default:
                    idCurrentDate = 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for(int j= idCurrentDate+1;j<DayBarItemList.size();j++)
        {
            calendar1.add(Calendar.DAY_OF_YEAR, 1);
            Date nextDay = calendar1.getTime();

            DayBarItemList.get(j).setDayItem(nextDay);
        }

        for(int j = idCurrentDate-1;j>=0;j--)
        {
            calendar2.add(Calendar.DAY_OF_YEAR, -1);
            Date previousDay = calendar2.getTime();

            DayBarItemList.get(j).setDayItem(previousDay);
        }



    }


    @Override
    public void Update() {

    }
}

interface UpdateTask
{
    void Update();
}