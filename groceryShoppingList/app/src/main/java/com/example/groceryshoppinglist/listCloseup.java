package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class listCloseup extends AppCompatActivity {

    @Entity(tableName = "item_table")
    public class Item {

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "item_ID")
        private Integer mItemID;

        @NonNull
        @ColumnInfo(name = "item_Name")
        private String mItem;

        @NonNull
        @ColumnInfo(name = "Quantity")
        private String mQuantity;

        @NonNull
        @ColumnInfo(name = "ListID")
        private Integer mListID;

        @NonNull
        @ColumnInfo(name = "Category")
        private String mCategory;

        // Constructor
        public Item(@NonNull Integer itemID, @NonNull String item, @NonNull String quantity, @NonNull Integer listID, @NonNull String category) {
            this.mItemID = itemID;
            this.mItem = item;
            this.mQuantity = quantity;
            this.mListID = listID;
            this.mCategory = category;
        }

        //getter & setter methods of item
        public String getItemName(){return this.mItem;}
        public void setItemName(String name){this.mItem = name;}

        public String getQuantity(){return this.mQuantity;}
        public void setQuantity(String quantity){this.mQuantity = quantity;}

        public String getCategory(){return this.mCategory;}
        public void setCategory(String category){this.mCategory = category;}

        public Integer getListID(){return this.mListID;}
        public void setListID(Integer listID){this.mListID = listID;}

        public Integer getItemID(){return this.mItemID;}

    }










    public void addNewItem(View view){
        //get values from input
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("listCount", 0);
        String name = intent.getStringExtra("userName");

        Intent newItem = new Intent(this, addNewItem.class);
        newItem.putExtra("userName", name);
        newItem.putExtra("listCount", listCount);

        startActivity(newItem);
    }

    public void editItem(View view){
        Intent editItem = new Intent(this, editItems.class);

        startActivity(editItem);
    }

    public void backBtn(View view){
        //get values from input
        Intent intent = getIntent();
        int listCount = intent.getIntExtra("listCount", 0);
        String name = intent.getStringExtra("userName");

        Intent backBtn = new Intent(this, Overview.class);
        backBtn.putExtra("listCount", listCount);
        backBtn.putExtra("username", name);

        startActivity(backBtn);
    }

    /* Item functions until we create the list dynamically */
    public void editItem1(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);

        Intent editItem = new Intent(this, editItems.class);
        TextView item1 = (TextView) findViewById(R.id.item1);
        String itemName = item1.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem1);
        String itemValue = value1.getText().toString();

        editItem.putExtra("itemName", itemName);
        editItem.putExtra("itemValue", itemValue);
        editItem.putExtra("id", 1);
        editItem.putExtra("userName", name);
        editItem.putExtra("listCount", listCount);

        startActivity(editItem);
    }
    public void editItem2(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);

        Intent editItem = new Intent(this, editItems.class);
        TextView item1 = (TextView) findViewById(R.id.item2);
        String itemName = item1.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem2);
        String itemValue = value1.getText().toString();

        editItem.putExtra("itemName", itemName);
        editItem.putExtra("itemValue", itemValue);
        editItem.putExtra("id", 2);
        editItem.putExtra("userName", name);
        editItem.putExtra("listCount", listCount);

        startActivity(editItem);
    }
    public void editItem3(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);

        Intent editItem = new Intent(this, editItems.class);
        TextView item1 = (TextView) findViewById(R.id.item3);
        String itemName = item1.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem3);
        String itemValue = value1.getText().toString();

        editItem.putExtra("itemName", itemName);
        editItem.putExtra("itemValue", itemValue);
        editItem.putExtra("id", 3);
        editItem.putExtra("userName", name);
        editItem.putExtra("listCount", listCount);

        startActivity(editItem);
    }
    public void editItem4(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);

        Intent editItem = new Intent(this, editItems.class);
        TextView item1 = (TextView) findViewById(R.id.item4);
        String itemName = item1.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem4);
        String itemValue = value1.getText().toString();

        editItem.putExtra("itemName", itemName);
        editItem.putExtra("itemValue", itemValue);
        editItem.putExtra("id", 4);
        editItem.putExtra("userName", name);
        editItem.putExtra("listCount", listCount);

        startActivity(editItem);
    }
    public void editItem5(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);

        Intent editItem = new Intent(this, editItems.class);
        TextView item1 = (TextView) findViewById(R.id.item5);
        String itemName = item1.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem5);
        String itemValue = value1.getText().toString();

        editItem.putExtra("itemName", itemName);
        editItem.putExtra("itemValue", itemValue);
        editItem.putExtra("id", 5);
        editItem.putExtra("userName", name);
        editItem.putExtra("listCount", listCount);

        startActivity(editItem);
    }
    public void editItem6(View view){
        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        int listCount = intent.getIntExtra("listCount", 0);

        Intent editItem = new Intent(this, editItems.class);
        TextView item = (TextView) findViewById(R.id.item6);
        String itemName = item.getText().toString();

        CheckBox value1 = (CheckBox) findViewById(R.id.checkItem6);
        String itemValue = value1.getText().toString();

        editItem.putExtra("itemName", itemName);
        editItem.putExtra("itemValue", itemValue);
        editItem.putExtra("id", 6);
        editItem.putExtra("userName", name);
        editItem.putExtra("listCount", listCount);

        startActivity(editItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_closeup);

        //get values from input
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        String itemName = intent.getStringExtra("edited_name");
        String itemValue = intent.getStringExtra("edited_amount");
        int id = intent.getIntExtra("editedID", 0);


        String item5Name = intent.getStringExtra("item_name");
        String amount = intent.getStringExtra("item_amount");
        TextView item55 = (TextView) findViewById(R.id.item5);
        CheckBox itemAmount = (CheckBox) findViewById(R.id.checkItem5);

            item55.setText(item5Name);
            itemAmount.setText(amount);

        if(name != null) {
            TextView ownerName = (TextView) findViewById(R.id.listownerName2);
            ownerName.setText(String.valueOf(name));
        } else {
            String name1 = intent.getStringExtra("username");
            TextView ownerName = (TextView) findViewById(R.id.listownerName2);
            ownerName.setText(String.valueOf(name1));
        }

        switch (id){
            case 1:
                TextView item1 = (TextView) findViewById(R.id.item1);
                item1.setText(String.valueOf(itemName));
                CheckBox value1 = (CheckBox) findViewById(R.id.checkItem1);
                value1.setText(String.valueOf(itemValue));
                break;
            case 2:
                TextView item2 = (TextView) findViewById(R.id.item2);
                item2.setText(String.valueOf(itemName));
                CheckBox value2 = (CheckBox) findViewById(R.id.checkItem2);
                value2.setText(String.valueOf(itemValue));
                break;
            case 3:
                TextView item3 = (TextView) findViewById(R.id.item3);
                item3.setText(String.valueOf(itemName));
                CheckBox value3 = (CheckBox) findViewById(R.id.checkItem3);
                value3.setText(String.valueOf(itemValue));
                break;
            case 4:
                TextView item4 = (TextView) findViewById(R.id.item4);
                item4.setText(String.valueOf(itemName));
                CheckBox value4 = (CheckBox) findViewById(R.id.checkItem4);
                value4.setText(String.valueOf(itemValue));
                break;
            case 5:
                TextView item5 = (TextView) findViewById(R.id.item5);
                item5.setText(String.valueOf(itemName));
                CheckBox value5 = (CheckBox) findViewById(R.id.checkItem5);
                value5.setText(String.valueOf(itemValue));
                break;
            case 6:
                TextView item6 = (TextView) findViewById(R.id.item6);
                item6.setText(String.valueOf(itemName));
                CheckBox value6 = (CheckBox) findViewById(R.id.checkItem6);
                value6.setText(String.valueOf(itemValue));
                break;
        }


    }
}