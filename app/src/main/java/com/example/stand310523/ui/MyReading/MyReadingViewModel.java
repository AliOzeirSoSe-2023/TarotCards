package com.example.stand310523.ui.MyReading;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MyReadingViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public MyReadingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Reading fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}

