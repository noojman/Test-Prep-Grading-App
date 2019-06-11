package com.noojman.testprepgradingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {

    List<RowData> list = Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List<RowData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searchrow, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.publisher.setText(list.get(position).publisher);
        holder.testNumber.setText(String.format(Locale.getDefault(), "Test Number: %d", list.get(position).testNumber));
        holder.numProblems.setText(String.format(Locale.getDefault(), "# of Questions: %d", list.get(position).numProblems));

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public List<RowData> getData() {
        return list;
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, RowData data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(RowData data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}