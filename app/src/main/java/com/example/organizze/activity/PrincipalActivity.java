package com.example.organizze.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizze.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        FloatingActionButton fab1 = findViewById(R.id.fab);
        fab1.setOnClickListener(v -> Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());


    }

}

