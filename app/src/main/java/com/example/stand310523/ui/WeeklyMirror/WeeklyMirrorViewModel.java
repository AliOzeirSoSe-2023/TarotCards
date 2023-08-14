package com.example.stand310523.ui.WeeklyMirror;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeeklyMirrorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WeeklyMirrorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Weekly Mirror fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}