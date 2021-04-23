package com.example.groceryshoppinglist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "email")
    private String mEmail;

    @NonNull
    @ColumnInfo(name = "password")
    private String mPassword;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    // Constructor
    public User(@NonNull String mEmail, @NonNull String mPassword, @NonNull String mName) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mName = mName;
    }

    // Getters and setters
    public String getEmail() {
        return this.mEmail;
    }
    public void setEmail(String _email) {
        this.mEmail = _email;
    }

    public String getPassword() {
        return this.mPassword;
    }
    public void setPassword(String _password) {
        this.mPassword = _password;
    }

    public String getName() {
        return this.mName;
    }
    public void setName(String _name) {
        this.mName = _name;
    }

}
