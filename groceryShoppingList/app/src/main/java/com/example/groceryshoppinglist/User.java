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
    public User(@NonNull String _email, @NonNull String _password, @NonNull String _name) {
        this.mEmail = _email;
        this.mPassword = _password;
        this.mName = _name;
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
