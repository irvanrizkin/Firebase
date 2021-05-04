package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailText, passwordText;
    ImageView signInButton;
    TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = findViewById(R.id.main_text_email);
        passwordText = findViewById(R.id.main_text_password);

        signInButton = findViewById(R.id.main_button_signin);

        signUpLink = findViewById(R.id.main_link_signup);

        FirebaseAuth fireAuth;

        fireAuth = FirebaseAuth.getInstance();

        if (fireAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, ProfileActivity.class));
        }

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged In",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    startActivity(new Intent(
                                            getApplicationContext(),
                                            ProfileActivity.class
                                    ));
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            task.getException().getMessage(),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }
                        }
                );
            }
        });
    }
}