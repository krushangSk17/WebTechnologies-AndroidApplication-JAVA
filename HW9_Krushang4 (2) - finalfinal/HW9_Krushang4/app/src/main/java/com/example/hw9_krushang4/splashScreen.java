package com.example.hw9_krushang4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        // Set the content view to your splash screen layout
        setContentView(R.layout.activity_splash_screen);
        // Create a new Handler object to delay execution
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);

                // Close the SplashScreenActivity
                finish();
            }
        }, 3000); // Delay for 3 seconds (3000 milliseconds)
    }
}
