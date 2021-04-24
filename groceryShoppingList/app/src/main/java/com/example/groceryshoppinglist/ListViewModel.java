package com.example.groceryshoppinglist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private ListRepository mRepository;

    private final LiveData<List<ListClass>> mListsByOwner;

    public ListViewModel (@NonNull Application application, String ownerEmail){
        super(application);

        mRepository = new ListRepository(application);
        mListsByOwner = mRepository.getListsByOwner(ownerEmail);
    }

    LiveData<List<ListClass>> getListsByOwner(String ownerEmail){
        return mRepository.getListsByOwner(ownerEmail);
    }

    public void insert(ListClass list) {mRepository.insert(list);}
}
