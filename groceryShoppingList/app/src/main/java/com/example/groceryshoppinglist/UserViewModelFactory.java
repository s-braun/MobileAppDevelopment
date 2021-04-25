package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String mEmail;

    public UserViewModelFactory(@NonNull Application application, String email) {
        mApplication = application;
        mEmail = email;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(mApplication, mEmail);
    }
}
