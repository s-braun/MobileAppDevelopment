package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        //make text boxes intake data
        final EditText editItem = findViewById(R.id.editItem);
        final EditText editAmount = findViewById(R.id.editItemAmount);
        String editedName = editItem.getText().toString();
        String editedAmount = editAmount.getText().toString();

        //configure edit items button
        final Button ConfirmButton = findViewById(R.id.confirmEditBtn22);
        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (editItems.this, listCloseup.class);
                intent.putExtra("edited_name", editedName);
                intent.putExtra("edited_amount", editedAmount);
                startActivity(intent);
            }
        });

        //configure cancel button
        final Button CancelButton = findViewById(R.id.cancelButton2);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent (editItems.this, listCloseup.class);
                startActivity(intent);
            }
        });
    }
}