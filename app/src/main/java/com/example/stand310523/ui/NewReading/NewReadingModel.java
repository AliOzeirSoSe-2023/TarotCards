package com.example.stand310523.ui.NewReading;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewReadingModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NewReadingModel() {
        mText = new MutableLiveData<>();
            mText.setValue("This is New Reading Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}