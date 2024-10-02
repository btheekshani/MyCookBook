package com.example.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Delay for 5 seconds before transitioning to LoginActivity
        new Handler().postDelayed(() -> {
            // Start LoginActivity
            Intent intent = new Intent(MainActivity.this, StartCooking.class);
            startActivity(intent);  // Use the built-in startActivity method
            finish(); // Finish the splash activity
        }, 5000); // 5000 milliseconds = 5 seconds
    }
}
