package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class ProfileActivicty extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button settingsButton;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activicty);

        setTitle("Groups");
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            password = null;
        }else {
            password = extras.getString("password");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() ==null)
        {
            finish();
            startActivity(new Intent(this, loginactivicty.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome:  " +user.getEmail());
        settingsButton = findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view == settingsButton) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            intent.putExtra("password", password);
            startActivity(intent);

            //startActivity(new Intent(this, SettingsActivity.class));
        }

    }
}
