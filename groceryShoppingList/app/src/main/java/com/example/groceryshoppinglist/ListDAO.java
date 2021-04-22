package com.example.groceryshoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Delete
    void delete(List user);
}
