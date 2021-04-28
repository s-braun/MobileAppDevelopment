package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository mRepository;

    private final LiveData<List<Item>> mAllItems;
    private LiveData<List<Item>> mItemsByListID;
    private LiveData<List<Item>> mItemsByCategoryAndListID;

    public ItemViewModel (@NonNull Application application, int listID){
        super(application);

        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
        mItemsByListID = mRepository.getItemsByListID(listID);
       /* mItemsByCategoryAndListID = mRepository.getItemsByCategoryAndList(category, listID);*/
    }

    LiveData<List<Item>> getAllItems() {return mAllItems;}

    LiveData<List<Item>> getItemsByListID(Integer listID){
        return mRepository.getItemsByListID(listID);
    }

    LiveData<List<Item>> getItemsByCategoryAndListID(String category, int listID){
        return mRepository.getItemsByCategoryAndList(category, listID);
    }

    public void insert(Item item) {mRepository.insert(item);}

    public void updateItemValues(int id, String name, String amount, String category) { mRepository.updateItemValues(id, name, amount, category); }

    public void deleteItem(int itemID) {mRepository.deleteItem(itemID);}

    public void updateIsChecked(Boolean isChecked, String name, String amount, String category) {mRepository.updateIsChecked(isChecked, name, amount, category);}
}
