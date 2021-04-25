package com.example.groceryshoppinglist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM item_table ")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM item_table WHERE ListID = :listID ORDER BY Category ASC")
    LiveData<List<Item>> getItemsByListID(Integer listID);

    @Query("SELECT * FROM item_table WHERE Category = :category AND ListID = :listID ")
    LiveData<List<Item>> getItemsByCategoryAndList(String category, Integer listID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("UPDATE item_table SET item_Name = :newName, Quantity = :newQuantity, Category = :newCategory WHERE item_ID = :itemID")
    void updateItemValues(int itemID, String newName, String newQuantity, String newCategory);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateItem(Item item);

}
