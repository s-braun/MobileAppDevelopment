package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;
    private String mEmail;

    private final LiveData<User> mUserInfo;
    private final LiveData<List<User>> mAllUsers;
    private final LiveData<List<ListClass>> mUserLists;

    public UserViewModel (Application application, String email) {
        super(application);
        mRepository = new UserRepository(application);
        mEmail = email;

        mUserInfo = mRepository.getmUserInfo(email);
        mAllUsers = mRepository.getmAllUsers();
        mUserLists = mRepository.getmUserLists(email);
    }

    LiveData<User> getmUserInfo(String email) {
        return mRepository.getmUserInfo(email);
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
