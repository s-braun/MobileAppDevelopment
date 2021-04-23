package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ItemViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String mCategory;
    private int mListID;

    public ItemViewModelFactory(@NonNull Application application, String category, int listID){
        mApplication = application;
        mCategory = category;
        mListID = listID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new ItemViewModel(mApplication, mCategory, mListID);
    }
}
