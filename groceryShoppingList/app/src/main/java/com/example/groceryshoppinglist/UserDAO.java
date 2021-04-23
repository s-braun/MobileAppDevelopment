package com.example.groceryshoppinglist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDAO {

    // Get all info about a user
    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    User getUserInfo(String email);

    @Query("SELECT * FROM user_table")
    User[] getAllUsers();

    // Made this using this: https://learnsql.com/blog/how-to-join-3-tables-or-more-in-sql/
    // Joins the user, listuser, and list entity to get a user's lists
    @Query("SELECT list_table.list_ID, list_table.owner_email " +
            "FROM list_table " +
            "JOIN list_user_table " +
                "ON list_table.list_ID = list_user_table.list_ID " +
            "JOIN user_table " +
                "ON user_table.email = list_user_table.email " +
            "WHERE user_table.email = :email")
    List<ListClass> getUserLists(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Delete
    void delete(User user);

}
