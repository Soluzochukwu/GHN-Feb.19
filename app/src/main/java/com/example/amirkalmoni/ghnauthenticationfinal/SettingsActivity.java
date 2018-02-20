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

public class SettingsActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private int noOfOptions = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //Initialise list view
        listView = findViewById(R.id.settingsListView);

        //Create list
        String[] options = new String[] {user.getEmail(), "Account", "Groups", "Notifications", "Data and Storage", "Help"};
        ArrayList<String> listOfOptions = new ArrayList<String>();
        listOfOptions.addAll(Arrays.asList(options));

        //Create and Set ArrayAdapter
        adapter = new ArrayAdapter<String>(this, R.layout.simplerow, listOfOptions);
        listView.setAdapter(adapter);
        click();
    }

    private void click(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int optionPosition = i;
                for(int index = 1; index < noOfOptions; index++){
                    if(index == optionPosition){
                        String optionName = (String)listView.getItemAtPosition(index);
                        nextActivity(optionName);
                        break;
                    }
                }
            }
        });
    }

    private void nextActivity(String selectedOption){
        Intent intent = null;
        if(selectedOption.equalsIgnoreCase("Account"))
            intent = new Intent(this, Account_Settings.class);
        if(selectedOption.equalsIgnoreCase("Groups"))
            intent = new Intent(this, Groups_Settings.class);
        if(selectedOption.equalsIgnoreCase("Notifications"))
            intent = new Intent(this, Notification_Settings.class);
        if(selectedOption.equalsIgnoreCase("Data and Storage"))
            intent = new Intent(this, DS_Settings.class);
        if(selectedOption.equalsIgnoreCase("Help"))
            intent = new Intent(this, Help_Settings.class);
        startActivity(intent);



    }

}
