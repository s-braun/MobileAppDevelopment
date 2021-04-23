package com.example.groceryshoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM item_table ")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM item_table WHERE Category = :category ")
    LiveData<List<Item>> getItemsByCategory(String category);

    @Query("SELECT * FROM item_table WHERE Category = :category AND ListID = :listID ")
    LiveData<List<Item>> getItemsByCategoryAndList(String category, Integer listID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("UPDATE item_table SET item_Name = :newName WHERE item_ID = :itemID")
    void updateName(String newName, int itemID);

    @Query("UPDATE item_table SET Quantity = :newQuantity WHERE item_ID = :itemID")
    void updateQuantity(String newQuantity, int itemID);

}
