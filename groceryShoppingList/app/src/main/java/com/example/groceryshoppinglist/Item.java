package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "item_ID")
    private int mItemID;

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

    @NonNull
    @ColumnInfo(name = "checked")
    private Boolean mIsChecked;

    // Constructor
    public Item(Integer itemID, @NonNull String item, @NonNull String quantity, @NonNull Integer listID, @NonNull String category, @NonNull Boolean isChecked) {
        this.mItemID = itemID;
        this.mItem = item;
        this.mQuantity = quantity;
        this.mListID = listID;
        this.mCategory = category;
        this.mIsChecked = isChecked;
    }

    //getter & setter methods of item
    public String getItem(){return this.mItem;}

    public String getItemName(){return this.mItem;}
    public void setItemName(String name){this.mItem = name;}

    public String getQuantity(){return this.mQuantity;}
    public void setQuantity(String quantity){this.mQuantity = quantity;}

    public String getCategory(){return this.mCategory;}
    public void setCategory(String category){this.mCategory = category;}

    public Integer getListID(){return this.mListID;}
    public void setListID(Integer listID){this.mListID = listID;}

    public int getItemID(){return this.mItemID;}
    public void setItemID(int itemID){this.mItemID = itemID;}

    public Boolean getIsChecked() {return this.mIsChecked;}
    public void setIsChecked(Boolean check){this.mIsChecked = check;}

}