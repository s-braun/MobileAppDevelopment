package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ListViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String mOwnerEmail;

    public ListViewModelFactory(@NonNull Application application, String ownerEmail){
        mApplication = application;
        mOwnerEmail = ownerEmail;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new ListViewModel(mApplication, mOwnerEmail);
    }
}
