package com.charanajayworks.harrypotterquiz;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FirstActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    Button playOption, leaderBoardOption, settingsOption, submitQuestionOpt;
    ImageView googleGameOption, rateButton, shareButton;
    MediaPlayer clickSound;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_screen);

        playOption = findViewById(R.id.playOpt);
        leaderBoardOption = findViewById(R.id.leaderboardOpt);
        settingsOption = findViewById(R.id.settingsOpt);
        submitQuestionOpt = findViewById(R.id.submitquesOpt);
        googleGameOption =  findViewById(R.id.googleGameOption);
        rateButton = findViewById(R.id.rateButton);
        shareButton = findViewById(R.id.shareButton);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/oldtricks.ttf");
        playOption.setTypeface(font);
        leaderBoardOption.setTypeface(font);
        settingsOption.setTypeface(font);
        submitQuestionOpt.setTypeface(font);

        playOption.setOnTouchListener(this);
        leaderBoardOption.setOnTouchListener(this);
        settingsOption.setOnTouchListener(this);
        submitQuestionOpt.setOnTouchListener(this);
        googleGameOption.setOnTouchListener(this);
        rateButton.setOnTouchListener(this);
        shareButton.setOnTouchListener(this);

        clickSound = MediaPlayer.create(this,R.raw.click);

        playOption.setOnClickListener(this);
        leaderBoardOption.setOnClickListener(this);
        settingsOption.setOnClickListener(this);
        submitQuestionOpt.setOnClickListener(this);
        googleGameOption.setOnClickListener(this);
        rateButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.getBackground().setColorFilter(Color.parseColor("#9E9E9E"), PorterDuff.Mode.SRC_ATOP);
            view.invalidate();
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
            case R.id.playOpt :
                clickSound.start();
                Intent intent = new Intent(this, ModeSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.leaderboardOpt :
                clickSound.start();
                break;
            case R.id.settingsOpt :
                clickSound.start();
                break;
            case R.id.submitquesOpt :
                clickSound.start();
                break;
            case R.id.googleGameOption :
                clickSound.start();
                break;
            case R.id.rateButton :
                clickSound.start();
                rateapp();
                break;
            case R.id.shareButton :
                clickSound.start();
                String str = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Are you a Potterhead ? Test your Harry Potter knowledge by playing this quiz game.\n\nDownload : " + str);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
    }

    private void rateapp() {
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }

    private void rateAppPopUp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RateMyApp();
            }
        }, 2000);
    }


    private void RateMyApp(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("HP_QUIZ", MODE_PRIVATE);
        int rate = sharedPreferences.getInt("rate_now", 0);
        Log.d("RATE_POP", rate+"");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("rate_now", rate+1);
        editor.apply();

        if(rate == 5){
            RateMyAppAlert();
        }else if(rate%12 == 0 && rate!=0){
            RateMyAppAlert();
        }
    }


    private void RateMyAppAlert(){
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setMessage("Do you like the app ? \nTake some time to review and vote us")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        rateapp();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

}
