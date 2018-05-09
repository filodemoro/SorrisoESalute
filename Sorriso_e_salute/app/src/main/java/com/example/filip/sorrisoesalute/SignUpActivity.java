package com.example.filip.sorrisoesalute;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by filip on 16/03/2018.
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String LOGIN_KEY = "LOGIN_KEY";

    private SharedPreferences pref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        pref = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);



        //se attivazione va a buon fine
       // pref.edit().putBoolean(LOGIN_KEY, true).commit();


    }
}
