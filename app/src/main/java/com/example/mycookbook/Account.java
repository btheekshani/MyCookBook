package com.example.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        // Set insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up button navigation listeners
        ImageButton serButton = findViewById(R.id.imageButton10);
        serButton.setOnClickListener(view -> navigateTo(SearchRecipe.class));

        ImageButton comButton = findViewById(R.id.imageButton11);
        comButton.setOnClickListener(view -> navigateTo(Community.class));

        ImageButton notButton = findViewById(R.id.imageButton12);
        notButton.setOnClickListener(view -> navigateTo(Notification.class));

        ImageButton accButton = findViewById(R.id.imageButton13);
        accButton.setOnClickListener(view -> navigateTo(Account.class));

        ImageButton backButton = findViewById(R.id.imageButton2);
        backButton.setOnClickListener(view -> navigateTo(SearchRecipe.class));

        // Set up logout button listener
        Button logoutButton = findViewById(R.id.button4);
        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            navigateTo(SignInPage.class); // Navigate to SignInPage after logout
        });
    }

    // Helper method to navigate to a new activity
    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(Account.this, targetActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
