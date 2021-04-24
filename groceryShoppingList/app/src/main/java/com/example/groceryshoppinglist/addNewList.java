package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addNewList extends AppCompatActivity {

   public void addUser1(View view) {

        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.btnAdd1);

        addBtn.setImageResource(R.drawable.ic_baseline_check_circle_24);

    }
    public void addUser2(View view) {

        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.btnAdd2);

        addBtn.setImageResource(R.drawable.ic_baseline_check_circle_24);

    }
    public void addUser3(View view) {

        FloatingActionButton addBtn = (FloatingActionButton) findViewById(R.id.btnAdd3);

        addBtn.setImageResource(R.drawable.ic_baseline_check_circle_24);

    }

    public void cancel(View view){
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("numOfLists", 0);
        String name = intent.getStringExtra("userName");

        Intent cancel = new Intent(this, Overview.class);
        cancel.putExtra("listCount", listCount);
        cancel.putExtra("username", name);
        startActivity(cancel);
    }

    /*public void create(View view){
        //get values from input
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("numOfLists", 0);
        listCount++;
        String name = intent.getStringExtra("userName");

        Intent create = new Intent(this, Overview.class);
        create.putExtra("listCount", listCount);
        create.putExtra("username", name);
        startActivity(create);
    }*/

    public void sendInvitation(View view){
        EditText fstName = (EditText)findViewById(R.id.editFirstName);
        EditText lstName = (EditText)findViewById(R.id.editLastName);
        EditText mail = (EditText)findViewById(R.id.editEmail);

        String firstName = fstName.getText().toString();
        String lastName = lstName.getText().toString();
        String email = mail.getText().toString();



        Toast.makeText(this, "Invitation has been sent.", Toast.LENGTH_SHORT).show();
        return;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_list);

        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");

        TextView ownerName = (TextView) findViewById(R.id.listownerName);

        ownerName.setText(String.valueOf(name));


        final Button button = findViewById(R.id.btnCreate);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(name)) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                replyIntent.putExtra("ownerEmail", name);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }
}