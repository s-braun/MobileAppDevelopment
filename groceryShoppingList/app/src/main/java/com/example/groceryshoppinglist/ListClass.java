package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_table")
public class ListClass {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "list_ID")
    private Long mListID;

    @NonNull
    @ColumnInfo(name = "owner_email")
    private String mOwnerEmail;

    // Constructor
    public ListClass(@NonNull Long _id, @NonNull String _email) {
        this.mListID = _id;
        this.mOwnerEmail = _email;
    }

    // Getters and setters
    public String getOwnerEmail() {
        return this.mOwnerEmail;
    }
    public void setOwnerEmail(String _email) {
        this.mOwnerEmail = _email;
    }

}

