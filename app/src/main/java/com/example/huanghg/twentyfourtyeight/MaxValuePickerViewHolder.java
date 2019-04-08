package com.example.huanghg.twentyfourtyeight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MaxValuePickerViewHolder extends RecyclerView.ViewHolder {
    TextView tv_text;
    public MaxValuePickerViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_text = itemView.findViewById(R.id.tv_text);
    }

    public void updateView(int input){
        if (input == 0){
            tv_text.setText("-");
        }
        else {
            tv_text.setText(String.valueOf((int) Math.pow(2, input)));
        }
    }
}
