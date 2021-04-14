package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository mRepository;

    private final LiveData<List<listCloseup.Item>> mAllItems;

    public ItemViewModel (Application application){
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllWords();
    }

    LiveData<List<listCloseup.Item>> getAllItems() {return mAllItems;}

    public void insert(listCloseup.Item item) {mRepository.insert(item);}

}
