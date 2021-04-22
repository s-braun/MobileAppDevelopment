package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_user_table")
public class ListUser {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "list_user_ID")
    private Long mListUserID;

    @NonNull
    @ColumnInfo(name = "list_ID")
    private Integer mListID;

    @NonNull
    @ColumnInfo(name = "email")
    private String mEmail;

    // Constructor
    public ListUser(@NonNull Integer _listID, @NonNull String _email) {
        this.mListID = _listID;
        this.mEmail = _email;
    }

    // Getters and setters
    public Integer getListID() {
        return this.mListID;
    }
    public void setListID(Integer _listID) {
        this.mListID = _listID;
    }

    public String getEmail() {
        return this.mEmail;
    }
    public void setEmail(String _email) {
        this.mEmail = _email;
    }

}
