package com.example.huanghg.twentyfourtyeight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DimensionsPickerAdapter extends RecyclerView.Adapter<DimensionsPickerViewHolder> {

    @NonNull
    @Override
    public DimensionsPickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.three_item_picker, viewGroup, false);
        return new DimensionsPickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DimensionsPickerViewHolder dimensionsPickerViewHolder, int i) {
        dimensionsPickerViewHolder.updateView(i);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
