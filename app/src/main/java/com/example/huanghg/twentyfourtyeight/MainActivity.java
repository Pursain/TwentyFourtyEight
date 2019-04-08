package com.example.huanghg.twentyfourtyeight;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GameState.GameStateFunctions {
    private TextView testOutput;
    private GameState gm;
    private RecyclerView recyclerView;
    private GridAdapter gridAdapter;
    private int gameSize = 4;
    private boolean endlessMode = false;
    private int maxValue = 2048;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null){
            this.endlessMode = intent.getBooleanExtra("endlessMode", false);
            this.gameSize = intent.getIntExtra("dimensions", 4);
            this.maxValue = intent.getIntExtra("maxValue", 2048);
        }

        testOutput = findViewById(R.id.tv_board);
        recyclerView = findViewById(R.id.rv_board);
        reset();
    }

    private void reset(){
        gm = new GameState(this, gameSize);

        testOutput.setText(gm.boardAsString());

        recyclerView.setLayoutManager(new GridLayoutManager(this, gameSize){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        gridAdapter = new GridAdapter(gameSize, gm.boardAsMatrix());
        recyclerView.setAdapter(gridAdapter);
        recyclerView.setOnTouchListener(new OnSwipeListener(this){

            @Override
            public void OnSwipeUp() {
                gm.moveCommand(GameState.Direction.UP);
                testOutput.setText(gm.boardAsString());
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnSwipeDown() {
                gm.moveCommand(GameState.Direction.DOWN);
                testOutput.setText(gm.boardAsString());
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnSwipeLeft() {
                gm.moveCommand(GameState.Direction.LEFT);
                testOutput.setText(gm.boardAsString());
                gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnSwipeRight() {
                gm.moveCommand(GameState.Direction.RIGHT);
                testOutput.setText(gm.boardAsString());
                gridAdapter.notifyDataSetChanged();
            }
        });

        gm.startGame();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_up:
                gm.moveCommand(GameState.Direction.UP);
                break;
            case R.id.button_down:
                gm.moveCommand(GameState.Direction.DOWN);
                break;
            case R.id.button_left:
                gm.moveCommand(GameState.Direction.LEFT);
                break;
            case R.id.button_right:
                gm.moveCommand(GameState.Direction.RIGHT);
                break;
            case R.id.button_setSize:
                Log.d("mainActivity", "onClick: setSize");
                gameSize = Integer.valueOf(((EditText)findViewById(R.id.editText_sizeNumber)).getText().toString());
                reset();
                break;
        }
        testOutput.setText(gm.boardAsString());
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void movedComplete() {
        //animate stuff
        //use use gm.getLastInsertedBox()
    }
}
