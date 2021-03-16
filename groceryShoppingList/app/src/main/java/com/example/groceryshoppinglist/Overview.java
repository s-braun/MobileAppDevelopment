package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Overview extends AppCompatActivity {

    private Button[] lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Create button array for number of lists that user has
        lists = new Button[1];
    }


    public void openListActivity(int id) {
        Intent intent = new Intent(this, listCloseup.class);
        //intent.putExtra("listId", id);
        startActivity(intent);
    }

}