package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class Overview extends AppCompatActivity {

    private int numOfLists;
    private Button[] buttonListsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Create button array for number of lists that user has
        numOfLists = 3;
        buttonListsArray = new Button[numOfLists];

        //get numOfLists from addNewList after created a new one
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("listCount", 0);

        // set numOfLists to listCount from intent
        numOfLists = listCount;

        // Create the buttons, and listen for clicks
        createButtons();
        listListener();

        // Add list listener
        addListListener();
    }


    /*
    Create buttons for lists and give them functionality
    -----------------------------------------------------
     */

    private void createButtons() {
        // Get linear layout in var to add buttons to
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        // Add a button for each list
        for (int i = 0; i < numOfLists; i++) {
            buttonListsArray[i] = new Button(this);
            buttonListsArray[i].setText("Sample grocery list name");
            linearLayout.addView( buttonListsArray[i] );
        }
    }

    private void listListener() {
        for (int i = 0; i < numOfLists; i++) {

            int finalI = i;
            buttonListsArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the list activity and pass the id for the list or something!
                    openListActivity(finalI);
                }
            });
        }
    }

    private void openListActivity(int id) {
        Intent intent = new Intent(this, listCloseup.class);
        intent.putExtra("listId", id);
        startActivity(intent);
    }


    /*
    Add New List Button
    --------------------
     */

    private void addListListener() {
        Button buttonAddNewList = findViewById(R.id.buttonAddNewList);
        buttonAddNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openAddNewListActivity();
            }
        });
    }

    private void openAddNewListActivity() {
        Intent intent = new Intent(this, addNewList.class);
        startActivity(intent);
    }

}