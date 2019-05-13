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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.noojman.testprepgradingapp.R;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
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
    private EditText textInputConfirmPassword;

    private FirebaseAuth mAuth;

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            textInputEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            textInputEmail.requestFocus();
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditableText().toString().trim();
        String passwordConfirmInput = textInputConfirmPassword.getEditableText().toString().trim();

        if (!passwordInput.equals(passwordConfirmInput))
        {
            textInputConfirmPassword.setError("Password does not match");
            textInputConfirmPassword.requestFocus();
            return false;
        } else if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            textInputPassword.requestFocus();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            textInputPassword.requestFocus();
            return false;
        } else {
            textInputPassword.setError(null);
            textInputConfirmPassword.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        textInputEmail = findViewById(R.id.register_email);
        textInputPassword = findViewById(R.id.create_password);
        textInputConfirmPassword = findViewById(R.id.confirm_password);

        Button createAccountButton = findViewById(R.id.button_create_account);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail() && validatePassword())
                {
                    mAuth.createUserWithEmailAndPassword(
                            textInputEmail.getEditableText().toString().trim(),
                            textInputPassword.getEditableText().toString().trim())
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("RegisterActivity", "createUserWithEmail:success");

                                        //FirebaseUser user = mAuth.getCurrentUser();

                                        Toast.makeText(RegisterActivity.this, "Account successfully created",
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("RegisterActivity", "createUserWithEmail:failure", task.getException());

                                        try {
                                            throw task.getException();
                                        } catch(FirebaseAuthWeakPasswordException e) {
                                            textInputPassword.setError("Password too weak");
                                            textInputPassword.requestFocus();
                                        } catch(FirebaseAuthInvalidCredentialsException e) {
                                            textInputEmail.setError("Please enter a valid email address");
                                            textInputEmail.requestFocus();
                                        } catch(FirebaseAuthUserCollisionException e) {
                                            textInputEmail.setError("User already exists");
                                            textInputEmail.requestFocus();
                                        } catch(Exception e) {
                                            Log.e("RegisterActivity", e.getMessage());
                                        }
                                    }
                                }
                            });
                }
            }
        });
    }
}
