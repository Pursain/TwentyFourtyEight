package com.example.huanghg.twentyfourtyeight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.LinearLayout;

public class CustomGamePicker extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "CustomGamePicker";

    private RecyclerView rv_maxValue;
    private RecyclerView rv_dimensions;
    private LinearLayout ll_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game_picker);

        rv_maxValue = findViewById(R.id.rv_maxValue);

        rv_maxValue.setLayoutManager(new LinearLayoutManager(this));
        rv_maxValue.setAdapter(new MaxValuePickerAdapter());

        SnapHelper maxValueSnapHelper = new PagerSnapHelper();
        maxValueSnapHelper.attachToRecyclerView(rv_maxValue);

        rv_dimensions = findViewById(R.id.rv_dim);

        rv_dimensions.setLayoutManager(new LinearLayoutManager(this));
        rv_dimensions.setAdapter(new DimensionsPickerAdapter());

        SnapHelper dimensionsSnapHelper = new PagerSnapHelper();
        dimensionsSnapHelper.attachToRecyclerView(rv_dimensions);
    }

    @Override
    public void onClick(View v) {
        int maxValue = ((LinearLayoutManager)rv_maxValue.getLayoutManager()).findFirstCompletelyVisibleItemPosition()+1;
        int dimensions = ((LinearLayoutManager)rv_dimensions.getLayoutManager()).findFirstCompletelyVisibleItemPosition()+2;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("endlessMode", false);
        intent.putExtra("dimensions", dimensions);
        intent.putExtra("maxValue", maxValue);
        startActivity(intent);
    }
}
