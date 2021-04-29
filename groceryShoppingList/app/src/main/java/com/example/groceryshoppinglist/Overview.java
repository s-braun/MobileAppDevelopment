package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Overview extends AppCompatActivity {

    private String ownerName, user1, user2, user3;
    private ArrayList<String> userNames= new ArrayList<String>();
    private ListViewModel mListViewModel;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    private BroadcastReceiver mUser1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            user1 = intent.getStringExtra("user1");
        }
    };
    private BroadcastReceiver mUser2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            user2 = intent.getStringExtra("user2");
        }
    };
    private BroadcastReceiver mUser3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            user3 = intent.getStringExtra("user3");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        //get users from broadcast
        LocalBroadcastManager.getInstance(this).registerReceiver(mUser1, new IntentFilter("USER1"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mUser2, new IntentFilter("USER2"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mUser3, new IntentFilter("USER3"));

        try {
            // Get intents, including the number of lists and username of the user
            Intent intent = getIntent();
            ownerName = intent.getExtras().getString("username", "");
            userNames = intent.getStringArrayListExtra("userNames");

            // Personalize the text view using username
            TextView title = findViewById(R.id.listOwnerName);
            title.setText(ownerName + "'s Lists");
        } catch (Exception e) {
            Toast.makeText(this, "No data received. " + e, Toast.LENGTH_SHORT).show();
            return;
        }

        // Add new list button listener
        addListListener();
        logoutButton();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ListclassListAdapter adapter = new ListclassListAdapter(new ListclassListAdapter.ListDiff(), ownerName);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListViewModelFactory factory = new ListViewModelFactory(this.getApplication(), ownerName);
        mListViewModel = new ViewModelProvider(this, factory).get(ListViewModel.class);

        mListViewModel.getListsByOwner(ownerName).observe(this, items -> {
            adapter.submitList(items);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //checks if different users have been added
        if (requestCode == NEW_ITEM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

                if(!user1.isEmpty() && !user2.isEmpty()){
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"), user1, user2);
                    mListViewModel.insert(list);
                } else if(!user1.isEmpty() && !user3.isEmpty()){
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"), user1, user3);
                    mListViewModel.insert(list);
                } else if(!user2.isEmpty() && !user3.isEmpty()){
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"), user2, user3);
                    mListViewModel.insert(list);
                } else if (!user1.isEmpty()){
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"), user1);
                    mListViewModel.insert(list);
                } else if (!user2.isEmpty()){
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"), user2);
                    mListViewModel.insert(list);
                } else if (!user3.isEmpty()){
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"), user3);
                    mListViewModel.insert(list);
                } else {
                    ListClass list = new ListClass(data.getStringExtra("ownerEmail"));
                    mListViewModel.insert(list);
                }

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
        intent.putExtra("userName", ownerName);
        startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
    }

    public void logoutButton() {
        Button buttonAddNewList = findViewById(R.id.logoutButton);
        buttonAddNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    public void logout() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}