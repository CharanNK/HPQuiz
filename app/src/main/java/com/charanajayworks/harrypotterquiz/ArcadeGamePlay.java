package com.charanajayworks.harrypotterquiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class ArcadeGamePlay extends AppCompatActivity {
    Button option1button,option2button,option3button,option4button;
    TextView questions_textview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arcade_mode_gameplay);

        option1button = findViewById(R.id.option1button);
        option2button = findViewById(R.id.option2button);
        option3button = findViewById(R.id.option3button);
        option4button = findViewById(R.id.option4button);

        questions_textview = findViewById(R.id.questions_textview);

        Typeface medieval_font = Typeface.createFromAsset(getAssets(), "fonts/faune.otf");
        option1button.setTypeface(medieval_font);
        option2button.setTypeface(medieval_font);
        option3button.setTypeface(medieval_font);
        option4button.setTypeface(medieval_font);

        questions_textview.setTypeface(medieval_font);
    }
}
