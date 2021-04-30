package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class DayBarItemAdapter extends  RecyclerView.Adapter<DayBarItemAdapter.DayBarItemHolder> {

    private Context mContext;
    private List<DayBarItem> mListItem;
    private OnDayBarClickListener mDayBarListener;

    int clickedPosition;
    boolean clicked = false;


    public DayBarItemAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<DayBarItem> getmListItem() {
        return mListItem;
    }

    public void setmListItem(List<DayBarItem> mListItem) {
        this.mListItem = mListItem;
    }

    public void setData(List<DayBarItem> list)
    {
        this.mListItem = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DayBarItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_bar_item,parent,false);
        return new DayBarItemHolder(view,mDayBarListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final DayBarItemHolder holder, final int position) {
        final DayBarItem item = mListItem.get(position);
        if(item==null) return;
        holder.buttonItem.setText(item.getDayItem().toString().substring(8,10));
        holder.textItem.setText(item.getDayItem().toString().substring(0,3));

        if(clicked){
            if(position == clickedPosition)
                holder.buttonItem.setBackgroundResource(R.drawable.button_circle_choosed);
            else holder.buttonItem.setBackgroundResource(R.drawable.color_choose);
        }
        else
        {
            clicked =true;
            Date currentDate = new Date(System.currentTimeMillis());
            switch (currentDate.toString().substring(0, 3)) {
                case "Mon": {
                    clickedPosition=0;

                }
                break;
                case "Tue": {
                    clickedPosition=1;

                }
                break;
                case "Wed": {
                    clickedPosition=2;

                }
                break;
                case "Thu": {
                    clickedPosition=3;

                }
                break;
                case "Fri": {
                    clickedPosition=4;

                }
                break;
                case "Sat": {
                    clickedPosition=5;

                }
                break;
                case "Sun": {
                    clickedPosition=6;

                }
                break;
                default:
                    clickedPosition=0;

            }

        }

        holder.buttonItem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")

            @Override
            public void onClick(View v) {
//                holder.buttonItem.setBackgroundResource(R.drawable.button_circle_choosed);
                mDayBarListener.onDayBarClick(mListItem.get(position));
                clickedPosition = position;
                clicked = true;
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListItem!=null) return mListItem.size();
        return 0;
    }


    void setmDayBarListener(OnDayBarClickListener onDayBarClickListener)
    {
        this.mDayBarListener = onDayBarClickListener;
    }
    public class DayBarItemHolder extends RecyclerView.ViewHolder {

        private TextView textItem;

        private Button buttonItem;


        public DayBarItemHolder(@NonNull View itemView,final OnDayBarClickListener listener) {
            super(itemView);
            textItem = itemView.findViewById(R.id.textDayBarItem);
            buttonItem = itemView.findViewById(R.id.buttonDayBarItem);

        }



    }

    public interface  OnDayBarClickListener
    {
        void onDayBarClick(DayBarItem choosedItem);
    }


//    public void setOnClickListener(OnDayBarClickListener listener) {
//        mDayBarListener = listener;
//    }
}
