package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.noojman.testprepgradingapp.MainActivity;
import com.noojman.testprepgradingapp.R;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +   // start-of-string
                    "(?=.*[0-9])" + // a digit must occur at least once
                    "(?=.*[a-z])" + // a lower case letter must occur at least once
                    "(?=.*[A-Z])" + // an upper case letter must occur at least once
                    "(?=\\S+$)" +   // no whitespace allowed in the entire string
                    ".{6,}" +       // at least six characters
                    "$");           // end-of-string

    private EditText textInputEmail;
    private EditText textInputPassword;

    private FirebaseAuth mAuth;

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            textInputEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Invalid email or password");
            textInputEmail.requestFocus();
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            textInputPassword.requestFocus();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputEmail.setError("Invalid email or password");
            textInputEmail.requestFocus();
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        textInputEmail = findViewById(R.id.login_email);
        textInputPassword = findViewById(R.id.login_password);

        Button signInButton = findViewById(R.id.button_sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail() && validatePassword())
                {
                    mAuth.signInWithEmailAndPassword(
                            textInputEmail.getEditableText().toString().trim(),
                            textInputPassword.getEditableText().toString().trim())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("LoginActivity", "signInWithEmail:success");

                                        //FirebaseUser user = mAuth.getCurrentUser();

                                        Toast.makeText(LoginActivity.this, "Sign in successful",
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("LoginActivity", "signInWithEmail:failure", task.getException());

                                        textInputEmail.setError("Invalid email or password");
                                        textInputEmail.requestFocus();
                                    }
                                }
                            });
                }
            }
        });

        TextView registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
