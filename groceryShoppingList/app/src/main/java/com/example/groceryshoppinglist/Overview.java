package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Overview extends AppCompatActivity {

    private String username;
    private int numOfLists;
    private Button[] buttonListsArray;
    private ListViewModel mListViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        try {
            // Get intents, including the number of lists and username of the user
            Intent intent = getIntent();
            numOfLists = intent.getIntExtra("listCount", 0);
            username = intent.getExtras().getString("username", "");

            // Create button array for number of lists that user has
            buttonListsArray = new Button[numOfLists];

            // Personalize the text view using username
            TextView title = findViewById(R.id.listOwnerName);
            title.setText(username + "'s Lists");
        } catch (Exception e) {
            Toast.makeText(this, "No data received. " + e, Toast.LENGTH_SHORT).show();
            return;
        }

        /*// Create the buttons for each list
        createButtons();
        // Listen for clicks on each list
        listListener();*/
        // Add new list button listener
        addListListener();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ListclassListAdapter adapter = new ListclassListAdapter(new ListclassListAdapter.ListDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListViewModelFactory factory = new ListViewModelFactory(this.getApplication(), username);
        mListViewModel = new ViewModelProvider(this, factory).get(ListViewModel.class);

        mListViewModel.getListsByOwner(username).observe(this, items -> {
            adapter.submitList(items);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //change listID here
        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ListClass list = new ListClass(data.getStringExtra("ownerEmail"));
            mListViewModel.insert(list);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


    /*
    Create buttons for lists and give them functionality
    -----------------------------------------------------
     */

    /*private void createButtons() {
        // Get linear layout in var to add buttons to
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 20, 0, 0);

        // Add a button for each list
        for (int i = 0; i < numOfLists; i++) {
            buttonListsArray[i] = new Button(this);
            buttonListsArray[i].setText("Sample grocery list " + i + "\nOwner's name ");
            buttonListsArray[i].setBackgroundColor(getResources().getColor(R.color.purple_500));
            buttonListsArray[i].setHeight(300);
            buttonListsArray[i].setTextColor(Color.WHITE);
            Drawable img = getResources().getDrawable( R.drawable.man );
            img.setBounds( 20, 0, 260, 240 );
            buttonListsArray[i].setCompoundDrawables(img,null, null, null);
            buttonListsArray[i].setLayoutParams(params);
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
        intent.putExtra("listCount", numOfLists);
        intent.putExtra("listId", id);
        intent.putExtra("userName", username);
        startActivity(intent);
    }*/



    /*Add New List Button
    --------------------*/


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
        intent.putExtra("userName", username);
        startActivity(intent);
    }

}