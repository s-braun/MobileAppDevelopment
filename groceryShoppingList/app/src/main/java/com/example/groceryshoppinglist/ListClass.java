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
    public ListClass(@NonNull Long mListID, @NonNull String mOwnerEmail) {
        this.mListID = mListID;
        this.mOwnerEmail = mOwnerEmail;
    }

    // Getters and setters
    public Long getListID() { return this.mListID; }
    public void setListID(Long _id) { this.mListID = _id; }

    public String getOwnerEmail() {
        return this.mOwnerEmail;
    }
    public void setOwnerEmail(String _email) {
        this.mOwnerEmail = _email;
    }

}

