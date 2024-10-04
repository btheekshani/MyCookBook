package com.example.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchRecipe extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ArrayList<FoodData> recipeList;

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

        recipeList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");

        // Load recipes from Firebase
        loadRecipes();

        // Back button functionality (navigates to LoginActivity)
        ImageButton backButton = findViewById(R.id.imageButton2);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(SearchRecipe.this, SignInPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close the current activity
        });

        // Define button actions for navigating to specific recipe activities
        Button next = findViewById(R.id.button15);
        next.setOnClickListener(view -> startActivity(new Intent(SearchRecipe.this, CheeseCakeRecipe.class)));

        Button pan = findViewById(R.id.button8);
        pan.setOnClickListener(view -> startActivity(new Intent(SearchRecipe.this, PanCakeRecipe.class)));

        Button piz = findViewById(R.id.button9);
        piz.setOnClickListener(view -> startActivity(new Intent(SearchRecipe.this, PizzaRecipe.class)));

        Button don = findViewById(R.id.button16);
        don.setOnClickListener(view -> startActivity(new Intent(SearchRecipe.this, DonutsRecipe.class)));

        // Community button
        ImageButton comButton = findViewById(R.id.imageButton11);
        comButton.setOnClickListener(view -> navigateToActivity(Community.class));

        // Notification button
        ImageButton notButton = findViewById(R.id.imageButton12);
        notButton.setOnClickListener(view -> navigateToActivity(Notification.class));

        // Account button
        ImageButton accButton = findViewById(R.id.imageButton13);
        accButton.setOnClickListener(view -> navigateToActivity(Account.class));

        // Add recipe button
        ImageButton addButton = findViewById(R.id.imageButton);
        addButton.setOnClickListener(view -> navigateToActivity(addrecipe1.class));
    }

    private void loadRecipes() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipeList.clear();
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    FoodData foodData = recipeSnapshot.getValue(FoodData.class);
                    recipeList.add(foodData);
                }
                // Update your UI with the recipeList data
                // For example, use RecyclerView or ListView to display the recipes
                // You can implement the RecyclerView adapter here
                Toast.makeText(SearchRecipe.this, "Recipes loaded: " + recipeList.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchRecipe.this, "Failed to load recipes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(SearchRecipe.this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
