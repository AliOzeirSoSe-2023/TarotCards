package com.example.stand310523.ui.LearnTarot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LearnTarotViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LearnTarotViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Explore the Mysteries of Major Arcana: Tarot Wisdom Unveiled");
    }

    public LiveData<String> getText() {
        return mText;
    }
}