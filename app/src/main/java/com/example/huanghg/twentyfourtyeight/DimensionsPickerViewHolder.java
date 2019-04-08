package com.example.huanghg.twentyfourtyeight;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DimensionsPickerViewHolder extends RecyclerView.ViewHolder {
    TextView tv_text;
    public DimensionsPickerViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_text = itemView.findViewById(R.id.tv_text);
    }

    public void updateView(int input){
        if (input == 0){
            tv_text.setText("-");
        }
        else {
            tv_text.setText(String.valueOf(input+1)+"x"+String.valueOf(input+1));
        }
    }
}
