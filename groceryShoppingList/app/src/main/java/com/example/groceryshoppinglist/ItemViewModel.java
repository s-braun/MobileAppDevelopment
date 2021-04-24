package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository mRepository;

    private final LiveData<List<Item>> mAllItems;
    private LiveData<List<Item>> mItemsByCategory;
    private LiveData<List<Item>> mItemsByCategoryAndListID;

    public ItemViewModel (@NonNull Application application, String category, int listID){
        super(application);

        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
        mItemsByCategory = mRepository.getItemsByCategory(category);
        mItemsByCategoryAndListID = mRepository.getItemsByCategoryAndList(category, listID);
    }

    LiveData<List<Item>> getAllItems() {return mAllItems;}

    LiveData<List<Item>> getItemsByCategory(String category){
        return mRepository.getItemsByCategory(category);
    }

    LiveData<List<Item>> getItemsByCategoryAndListID(String category, int listID){
        return mRepository.getItemsByCategoryAndList(category, listID);
    }

    public void insert(Item item) {mRepository.insert(item);}

    public void updateItemValues(int id, String name, String amount, String category) { mRepository.updateItemValues(id, name, amount, category); }
}
