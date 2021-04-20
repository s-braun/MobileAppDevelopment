package com.example.groceryshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewItemActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditItemView;
    private EditText mEditQuantity;
    private EditText mEditCategory;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        mEditItemView = findViewById(R.id.edit_item);
        mEditQuantity = findViewById(R.id.edit_quantity);
        mEditCategory = findViewById(R.id.edit_category);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditItemView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String item = mEditItemView.getText().toString();
                String quantity = mEditQuantity.getText().toString();
                String category = mEditCategory.getText().toString();
                replyIntent.putExtra("item", item);
                replyIntent.putExtra("quantity", quantity);
                replyIntent.putExtra("category", category);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
