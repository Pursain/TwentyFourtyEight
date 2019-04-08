package com.example.huanghg.twentyfourtyeight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MaxValuePickerAdapter extends RecyclerView.Adapter<MaxValuePickerViewHolder> {

    @NonNull
    @Override
    public MaxValuePickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_item_picker, viewGroup, false);
        return new MaxValuePickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaxValuePickerViewHolder maxValuePickerViewHolder, int i) {
        maxValuePickerViewHolder.updateView(i);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
