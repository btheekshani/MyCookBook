package com.example.mycookbook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInPage extends AppCompatActivity {

    EditText email, password, confirmPassword;
    Button regButton;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        // Initialize views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        regButton = findViewById(R.id.button4);
        progressBar = findViewById(R.id.progressBar);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // If user is already signed in, redirect to SearchRecipe activity
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), SearchRecipe.class));
            finish();
        }

        // Register button click listener
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();
                String confirmPasswordInput = confirmPassword.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(emailInput)) {
                    email.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(passwordInput)) {
                    password.setError("Password is required.");
                    return;
                }

                if (passwordInput.length() < 6) {
                    password.setError("Password must be greater than 6 characters.");
                    return;
                }

                if (!passwordInput.equals(confirmPasswordInput)) {
                    confirmPassword.setError("Passwords do not match.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Register the user in Firebase
                mAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignInPage.this, "User created.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(SignInPage.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Back button functionality (navigates to SkillLevel4 activity)
        ImageButton backButton = findViewById(R.id.imageButton6);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInPage.this, SkillLevel4.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }
}
