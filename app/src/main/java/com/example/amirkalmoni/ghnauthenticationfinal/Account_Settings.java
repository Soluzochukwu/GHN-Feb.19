package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Account_Settings extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private int noOfOptions = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        //Initialise list view
        listView = findViewById(R.id.accountListView);

        //Create list
        String[] options = new String[]{"Edit Account Information", "Log Out", "Delete Account"};
        ArrayList<String> listOfOptions = new ArrayList<String>();
        listOfOptions.addAll(Arrays.asList(options));

        //Create and Set ArrayAdapter
        adapter = new ArrayAdapter<String>(this, R.layout.simplerow, listOfOptions);
        listView.setAdapter(adapter);
        click();
    }

    private void click() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int optionPosition = i;
                for (int index = 0; index < noOfOptions; index++) {
                    if (index == optionPosition) {
                        String optionName = (String) listView.getItemAtPosition(index);
                        nextActivity(optionName);
                        break;
                    }
                }
            }
        });
    }

    private void nextActivity(String selectedOption) {
        Intent intent = null;
        if (selectedOption.equalsIgnoreCase("Edit Account Information"))
            intent = new Intent(this, EditProfile_Account.class);
        if (selectedOption.equalsIgnoreCase("Log Out") || selectedOption.equalsIgnoreCase("Delete Account")) {
            firebaseAuth.signOut();
            finish();
            intent = new Intent(this, loginactivicty.class);
        }
        startActivity(intent);
    }
}
