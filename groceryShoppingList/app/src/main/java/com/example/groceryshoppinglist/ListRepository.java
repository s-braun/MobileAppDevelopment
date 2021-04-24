package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ListRepository {

    private ListDAO mListDao;
    private LiveData<List<ListClass>> listsByOwner;
    private String ownerEmail;

    ListRepository(Application application) {
        GroceryDatabase db = GroceryDatabase.getDatabase(application);
        mListDao = db.listDAO();
        listsByOwner = mListDao.getListsByOwner(ownerEmail);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<ListClass>> getListsByOwner(String ownerEmail) {
        return mListDao.getListsByOwner(ownerEmail);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(ListClass list) {
        GroceryDatabase.databaseWriteExecutor.execute(() -> {
            mListDao.insert(list);
        });
    }

}
