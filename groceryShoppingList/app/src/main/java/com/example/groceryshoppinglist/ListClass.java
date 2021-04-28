package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_table")
public class ListClass {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "list_ID")
    private int mListID;

    @NonNull
    @ColumnInfo(name = "owner_email")
    private String mOwnerEmail;

    @ColumnInfo(name = "user1")
    private String mUser1;

    @ColumnInfo(name = "user2")
    private String mUser2;

    // Constructor
    public ListClass(@NonNull String mOwnerEmail) {
        this.mOwnerEmail = mOwnerEmail;
    }

    @Ignore
    public ListClass(@NonNull String mOwnerEmail, String user1) {
        this.mOwnerEmail = mOwnerEmail;
        mUser1 = user1;
    }

    @Ignore
    public ListClass(@NonNull String mOwnerEmail, String user1, String user2) {
        this.mOwnerEmail = mOwnerEmail;
        mUser1 = user1;
        mUser2 = user2;
    }

    // Getters and setters
    public int getListID() { return this.mListID; }
    public void setListID(int _id) { this.mListID = _id; }

    public String getOwnerEmail() {
        return this.mOwnerEmail;
    }
    public void setOwnerEmail(String _email) {
        this.mOwnerEmail = _email;
    }

    public String getUser1() {return mUser1;}
    public void setUser1(String user1) {mUser1 = user1;}

    public String getUser2() {return mUser2;}
    public void setUser2(String user2) {mUser2 = user2;}

}

