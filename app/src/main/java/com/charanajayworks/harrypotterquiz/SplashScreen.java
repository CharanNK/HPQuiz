package com.charanajayworks.harrypotterquiz;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Thread splashTread;
    TextView harry_potter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        harry_potter = findViewById(R.id.potterText);
//        Typeface font=Typeface.createFromAsset(getAssets(), "fonts/potty_harrer.TTF");
//        harry_potter.setTypeface(font);
        MediaPlayer mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
        StartAnimations();
    }

    private void StartAnimations() {
        {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
            anim.reset();
            LinearLayout l= findViewById(R.id.lin_lay);
            l.clearAnimation();
            l.startAnimation(anim);

            anim = AnimationUtils.loadAnimation(this, R.anim.trans_left_right);
            anim.reset();
            ImageView iv = findViewById(R.id.leftSwiping);
            iv.clearAnimation();
            iv.startAnimation(anim);

            Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.translate);
            anim.reset();
            ImageView iv1 = findViewById(R.id.rightSwiping);
            iv1.clearAnimation();
            iv1.startAnimation(anim1);

            Animation anim2 = AnimationUtils.loadAnimation(this,R.anim.fade_in_anim);
            anim2.reset();
            anim2.setStartOffset(2500);
            ImageView potterText = findViewById(R.id.potterText);
            potterText.clearAnimation();
            potterText.startAnimation(anim2);

            splashTread = new Thread() {
                @Override
                public void run() {
                    try {
                        int waited = 0;
                        // Splash screen pause time
                        while (waited < 6500) {
                            sleep(100);
                            waited += 100;
                        }
                        Intent intent = new Intent(SplashScreen.this,
                                FirstActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashScreen.this.finish();
                    } catch (InterruptedException e) {
                        // do nothing
                    } finally {
                        SplashScreen.this.finish();
                    }

                }
            };
            splashTread.start();

        }
    }
}
