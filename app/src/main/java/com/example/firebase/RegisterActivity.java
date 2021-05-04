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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText emailText, passwordText, nimText, namaText, kelasText;
    ImageView signUpButton;
    TextView signInLink;
    FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = findViewById(R.id.register_text_email);
        passwordText = findViewById(R.id.register_text_password);
        nimText = findViewById(R.id.register_text_nim);
        namaText = findViewById(R.id.register_text_nama);
        kelasText = findViewById(R.id.register_text_kelas);

        signUpButton = findViewById(R.id.register_button_signup);

        signInLink = findViewById(R.id.register_link_signin);

        fireAuth = FirebaseAuth.getInstance();
        

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                String nim = nimText.getText().toString();
                String nama = namaText.getText().toString();
                String kelas = kelasText.getText().toString();

                fireAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "New User Successfully Created",
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
                        });
            }
        });

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }


}