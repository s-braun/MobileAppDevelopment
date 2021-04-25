package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDAO mUserDao;
    private LiveData<User> mUserInfo;
    private LiveData<List<User>> mAllUsers;
    private LiveData<List<ListClass>> mUserLists;

    private String email;

    UserRepository(Application application) {
        GroceryDatabase db = GroceryDatabase.getDatabase(application);
        mUserDao = db.userDAO();
        mAllUsers = mUserDao.getAllUsers();
        mUserInfo = mUserDao.getUserInfo(email);
        mUserLists = mUserDao.getUserLists(email);
    }

    LiveData<List<User>> getmAllUsers() {
        return mAllUsers;
    }

    LiveData<User> getmUserInfo(String email) {
        return mUserDao.getUserInfo(email);
    }

    LiveData<List<ListClass>> getmUserLists(String email) {
        return mUserLists;
    }

    void insert(User user) {
        GroceryDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

}
