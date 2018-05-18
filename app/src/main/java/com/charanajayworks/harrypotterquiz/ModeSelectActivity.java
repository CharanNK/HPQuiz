package com.charanajayworks.harrypotterquiz;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ModeSelectActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    TextView classic_text,arcade_text,survival_text;
    CardView classic_cardview,arcade_cardview,survival_cardview;
    String TAG = "ModeSelectActivity";
    MediaPlayer clickSound;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemode_activity);

        classic_text = findViewById(R.id.classic_text);
        arcade_text = findViewById(R.id.arcade_text);
        survival_text = findViewById(R.id.survival_text);

        Typeface old_tricks_font = Typeface.createFromAsset(getAssets(), "fonts/oldtricks.ttf");
        classic_text.setTypeface(old_tricks_font);
        arcade_text.setTypeface(old_tricks_font);
        survival_text.setTypeface(old_tricks_font);

        classic_cardview = findViewById(R.id.classic_cardview);
        arcade_cardview  = findViewById(R.id.arcade_cardview);
        survival_cardview = findViewById(R.id.survival_cardview);

        clickSound = MediaPlayer.create(this,R.raw.click);

        classic_cardview.setOnTouchListener(this);
        arcade_cardview.setOnTouchListener(this);
        survival_cardview.setOnTouchListener(this);

        classic_cardview.setOnClickListener(this);
        arcade_cardview.setOnClickListener(this);
        survival_cardview.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG,"motion down");
            CardView card = (CardView) view;
            card.setBackgroundColor(Color.GRAY);
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.setBackground(getResources().getDrawable(R.drawable.category_select_buttons));
            view.invalidate();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.classic_cardview:
                clickSound.start();
                break;
            case R.id.arcade_cardview :
                clickSound.start();
                Intent intent = new Intent(this,ArcadeGamePlay.class);
                startActivity(intent);
                break;
            case R.id.survival_cardview:
                clickSound.start();
                break;
        }
    }
}
