package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    private ItemDAO mItemDao;
    private LiveData<List<listCloseup.Item>> mAllItems;
    private LiveData<List<listCloseup.Item>> itemsByCategory;
    private LiveData<List<listCloseup.Item>> itemsByCategoryAndList;

    ItemRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItemsByCategory();
        /*itemsByCategory = mItemDao.getItemsByCategory();
        itemsByCategoryAndList = mItemDao.getItemsByCategoryAndList();*/
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<listCloseup.Item>> getAllItems() {
        return mAllItems;
    }

    /*LiveData<List<listCloseup.Item>> getItemsByCategory() {
        return mAllItems;
    }*/

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(listCloseup.Item item) {
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.insert(item);
        });
    }


}
