package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDatabase db = Room.databaseBuilder(getApplicationContext(),
                GroceryDatabase.class, "database-name").build();

        loginListener();
    }

    public void loginListener() {
        Button btnLogin = findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openOverviewActivity();
            }
        });
    }

    private String getUsername() {
        EditText usernameInput = findViewById(R.id.username);
        return usernameInput.getText().toString();
    }

    private String getPassword() {
        EditText passwordInput = findViewById(R.id.password);
        return passwordInput.getText().toString();
    }

    public void openOverviewActivity() {
        Intent intent = new Intent(this, Overview.class);
        intent.putExtra("username", getUsername());
        startActivity(intent);
    }
}