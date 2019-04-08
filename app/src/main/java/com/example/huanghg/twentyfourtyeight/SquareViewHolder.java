package com.example.huanghg.twentyfourtyeight;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class SquareViewHolder extends RecyclerView.ViewHolder{
    TextView textView_num;
    CardView cardView;
    HashMap<Integer, Integer> colorMap = new HashMap<Integer, Integer>() {{
        put(0, Color.parseColor("#FFFFFF"));
        put(2, Color.parseColor("#E3F2FD"));
        put(4, Color.parseColor("#BBDEFB"));
        put(8, Color.parseColor("#90CAF9"));
        put(16, Color.parseColor("#64B5F6"));
        put(32, Color.parseColor("#42A5F5"));
        put(64, Color.parseColor("#2196F3"));
        put(128, Color.parseColor("#1E88E5"));
        put(256, Color.parseColor("#1976D2"));
        put(512, Color.parseColor("#1565C0"));
        put(1024, Color.parseColor("#0D47A1"));
        put(2048, Color.parseColor("#F44336"));
    }};

    public SquareViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_num = itemView.findViewById(R.id.tv_number);
        cardView = itemView.findViewById(R.id.cv_square);
    }

    public void updateView(int value){
        //given a value, give a display
        cardView.setCardBackgroundColor(colorMap.get(value));
        textView_num.setText(value!=0 ? String.valueOf(value) : "");

    }

}
