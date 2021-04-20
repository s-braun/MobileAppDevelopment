package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    private ItemDAO mItemDao;
    private LiveData<List<Item>> mAllItems;
    private LiveData<List<Item>> itemsByCategory;
    private LiveData<List<Item>> itemsByCategoryAndList;

    ItemRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItems();
        /*itemsByCategory = mItemDao.getItemsByCategory(category);
        itemsByCategoryAndList = mItemDao.getItemsByCategoryAndList(category, listID);*/
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Item>> getAllItems() {
        return mAllItems;
    }

    LiveData<List<Item>> getItemsByCategory() {
        return itemsByCategory;
    }

    LiveData<List<Item>> getItemsByCategoryAndList() {
        return itemsByCategoryAndList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Item item) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.insert(item);
        });
    }


}
