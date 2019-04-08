package com.example.huanghg.twentyfourtyeight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameSelectorPage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector_page);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_classicMode:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("endlessMode", false);
                intent.putExtra("dimensions", 4);
                intent.putExtra("maxValue", 2048);
                break;
            case R.id.btn_endlessMode:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("endlessMode", true);
                intent.putExtra("dimensions", 4);
                break;
            case R.id.btn_customMode:
                intent = new Intent(this, CustomGamePicker.class);
                break;
        }
        startActivity(intent);
    }
}
