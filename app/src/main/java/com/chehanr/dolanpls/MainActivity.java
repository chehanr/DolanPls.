package com.chehanr.dolanpls;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends Activity {

    Translator translator;
    EditText englishText;
    TextView dolanText, hintText;
    ImageButton about;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/comicsans.ttf");
        setContentView(R.layout.activity_main);


        ImageView bg = (ImageView) findViewById(R.id.bgimageView);

        Drawable[] drawables = new Drawable[]{
                getResources().getDrawable(R.drawable.bg_1),
                getResources().getDrawable(R.drawable.bg_2),
                getResources().getDrawable(R.drawable.bg_3),
                getResources().getDrawable(R.drawable.bg_4),
                getResources().getDrawable(R.drawable.bg_5),
        };

        activity = this;
        Random random = new Random();
        int randomNumber = random.nextInt(drawables.length);
        Drawable drawable = drawables[randomNumber];
        bg.setImageDrawable(drawable); //random background image on startup
        englishText = (EditText) findViewById(R.id.english_text);
        dolanText = (TextView) findViewById(R.id.dolan_text);
        hintText = (TextView) findViewById(R.id.hint_text);
        dolanText.setTypeface(tf);

        about = (ImageButton) findViewById(R.id.aboutButton);
        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        dolanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    clipboard.setText(dolanText.getText().toString());
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Dolan Translator", dolanText.getText().toString());
                    clipboard.setPrimaryClip(clip);
                }
                Toast.makeText(getBaseContext(), "Copied to clipboard.", Toast.LENGTH_SHORT).show();
                hintText.setVisibility(View.GONE);//clears the hint text
            }
        });


        dolanText.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), "Long clicked.", Toast.LENGTH_SHORT).show();

                hintText.setVisibility(View.GONE);//clears the hint text
                return true;

            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        englishText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable mEdit) {
                if (translator != null) {
                    translator.cancel(true);
                }

                translator = new Translator(activity);
                translator.execute(mEdit.toString());

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
}