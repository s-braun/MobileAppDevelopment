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
    private ListViewModel mListViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        try {
            // Get intents, including the number of lists and username of the user
            Intent intent = getIntent();
            username = intent.getExtras().getString("username", "");

            // Personalize the text view using username
            TextView title = findViewById(R.id.listOwnerName);
            title.setText(username + "'s Lists");
        } catch (Exception e) {
            Toast.makeText(this, "No data received. " + e, Toast.LENGTH_SHORT).show();
            return;
        }

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
        startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
    }

}