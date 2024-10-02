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

public class SearchRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Back button functionality (navigates to LoginActivity)
        ImageButton backButton = findViewById(R.id.imageButton2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchRecipe.this, SignInPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
        Button next = findViewById(R.id.button15);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define what happens when the button is clicked
                Intent intent = new Intent(SearchRecipe.this, CheeseCakeRecipe.class);
                startActivity(intent); // Navigate to CookingActivity
            }
        });

        Button pan = findViewById(R.id.button8);
        pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define what happens when the button is clicked
                Intent intent = new Intent(SearchRecipe.this, PanCakeRecipe.class);
                startActivity(intent); // Navigate to CookingActivity
            }
        });

        Button piz = findViewById(R.id.button9);
        piz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define what happens when the button is clicked
                Intent intent = new Intent(SearchRecipe.this, PizzaRecipe.class);
                startActivity(intent); // Navigate to CookingActivity
            }
        });

        Button don = findViewById(R.id.button16);
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define what happens when the button is clicked
                Intent intent = new Intent(SearchRecipe.this, DonutsRecipe.class);
                startActivity(intent); // Navigate to CookingActivity
            }
        });

        // Back button functionality (navigates to LoginActivity)
        ImageButton comButton = findViewById(R.id.imageButton11);
        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchRecipe.this, Community.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Back button functionality (navigates to LoginActivity)
        ImageButton notButton = findViewById(R.id.imageButton12);
        notButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchRecipe.this, Notification.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Back button functionality (navigates to LoginActivity)
        ImageButton accButton = findViewById(R.id.imageButton13);
        accButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchRecipe.this, Account.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });


    }
}