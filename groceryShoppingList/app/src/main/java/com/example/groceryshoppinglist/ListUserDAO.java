package com.example.groceryshoppinglist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ListUserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Delete
    void delete(ListUser listUser);
}
