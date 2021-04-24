package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private final LiveData<List<User>> mUserInfo;
    private final LiveData<List<User>> mAllUsers;
    private final LiveData<List<ListClass>> mUserLists;

    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);

        mUserInfo = mRepository.getmUserInfo();
        mAllUsers = mRepository.getmAllUsers();
        mUserLists = mRepository.getmUserLists();
    }

    LiveData<List<User>> getmUserInfo() {
        return mUserInfo;
    }

    LiveData<List<User>> getmAllUsers() {
        return mAllUsers;
    }

    LiveData<List<ListClass>> getmUserLists() {
        return mUserLists;
    }

    public void insert(User user) {
        mRepository.insert(user);
    }
}
