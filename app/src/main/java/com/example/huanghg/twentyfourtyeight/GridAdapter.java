package com.example.huanghg.twentyfourtyeight;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<SquareViewHolder> {

    private static final String TAG = "GridAdapter";

    private ArrayList<ArrayList<Box>> boxMatrix;
    private int size;


    public GridAdapter(int size, ArrayList<ArrayList<Box>> source) {
        this.size = size;
        this.boxMatrix = source;
        Log.d(TAG, "GridAdapter: the size of init boxMatrix is " + this.boxMatrix.size());

    }

    @NonNull
    @Override
    public SquareViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.square, viewGroup, false);
        return new SquareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquareViewHolder squareViewHolder, int i) {
        int row = i / size;
        int col = i % size;
        int value = this.boxMatrix.get(row).get(col).getValue();
        Log.d(TAG, "onBindViewHolder: row col: " + row + col);
        squareViewHolder.updateView(value);
    }

    @Override
    public int getItemCount() {
        return size*size;
    }
}
