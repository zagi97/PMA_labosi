package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    final int HEADER = -1;
    final int STUDENT = -2;
    List<Object> dataList;

    public MyRecyclerAdapter(List<Object> myDataset)
    {
        dataList = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        if(viewType == HEADER)
        {
            // create a new view
            View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_view, viewGroup, false);
            return new MyHeaderViewHolder(view);
        }
        else
        {
            View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_view, viewGroup, false);
            return new MyStudentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position)
    {
        if(getItemViewType(position) == HEADER)
        {
            MyHeaderViewHolder myHeaderHolder = (MyHeaderViewHolder) viewHolder;
            myHeaderHolder.tvStudent.setText(dataList.get(position).toString());
        }
        else
        {
            MyStudentViewHolder myStudentHolder = (MyStudentViewHolder) viewHolder;
            myStudentHolder.tvImeStudenta.setText(((Student) dataList.get(position)).getIme());
            myStudentHolder.tvPrezimeStudenta.setText(((Student)dataList.get(position)).getPrezime());
            myStudentHolder.tvNazivPredmeta.setText(((Student)dataList.get(position)).getPredmet());
        }
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if( dataList.get(position) instanceof Student )
        {
            return STUDENT;
        }
        else
        {
            return HEADER;
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvStudent;
        public MyHeaderViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvStudent = itemView.findViewById(R.id.tvStudenti);
        }
    }

    class MyStudentViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvImeStudenta;
        TextView tvPrezimeStudenta;
        TextView tvNazivPredmeta;
        public MyStudentViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvImeStudenta = itemView.findViewById(R.id.tvImeStudent);
            tvPrezimeStudenta = itemView.findViewById(R.id.tvPrezimeStudent);
            tvNazivPredmeta = itemView.findViewById(R.id.tvPredmetStudent);
        }
    }
}
