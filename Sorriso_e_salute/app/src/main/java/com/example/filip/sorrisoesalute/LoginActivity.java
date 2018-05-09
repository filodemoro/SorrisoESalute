package com.example.filip.sorrisoesalute;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by filip on 27/02/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_KEY = "LOGIN_KEY";
    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;
    private Button signUpButton;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);



        if (pref.getBoolean(LOGIN_KEY, false)) {
            //has login
            startActivity(new Intent(this, MainActivity.class));
            //must finish this activity (the login activity will not be shown when click back in main activity)
            finish();
        } else {

            //do something


            usernameField = findViewById(R.id.login_activity_username_editText);
            passwordField = findViewById(R.id.login_activity_password_editText);
            loginButton = findViewById(R.id.login_activity_login_button);
            signUpButton = findViewById(R.id.login_activity_signUp_button);


            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mark login

                    signIn(usernameField.getText().toString(), passwordField.getText().toString());
                }
            });

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mark login
                    startActivity(new Intent(getBaseContext(),SignUpActivity.class));
                    finish();
                }
            });

        }

    }



    private void signIn(String email, String password) {
        Log.d("TAG", "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        pref.edit().putBoolean(LOGIN_KEY, true).commit();
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();

        /*
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // [END sign_in_with_email]
        */
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = usernameField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            usernameField.setError("Required.");
            valid = false;
        } else {
            usernameField.setError(null);
        }

        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }
}
