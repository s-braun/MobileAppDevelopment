package com.example.groceryshoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM item_table ORDER BY Category")
    LiveData<List<listCloseup.Item>> getAllItemsByCategory();

    @Query("SELECT * FROM item_table WHERE Category = :category ")
    LiveData<List<listCloseup.Item>> getItemsByCategory(String category);

    @Query("SELECT * FROM item_table WHERE Category = :category AND ListID = :listID ")
    LiveData<List<listCloseup.Item>> getItemsByCategoryAndList(String category, Integer listID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(listCloseup.Item item);

}
