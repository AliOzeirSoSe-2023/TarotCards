package com.example.stand310523.ui.WeeklyMirror;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.stand310523.databinding.FragmentWeeklymirrorBinding;

import java.util.HashMap;
import java.util.Random;

public class WeeklyMirrorFragment extends Fragment {

    private static final int CARD_COUNT = 21;
    private FragmentWeeklymirrorBinding binding;
    private String userId;

    private final String[] imageResources = new String[]{
            "thefool", "themagician", "thehighpriestess", "theempress", "theemperor",
            "thehierophant", "thelovers", "thechariot", "strength", "thehermit",
            "theweeloffotune", "justice", "thehangedman", "death", "temperance",
            "thedevilevil", "thetower", "thestar", "themoon", "thesun", "judgement",
            "theworld",
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WeeklyMirrorViewModel weeklyMirrorViewModel =
                new ViewModelProvider(this).get(WeeklyMirrorViewModel.class);
        binding = FragmentWeeklymirrorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button revealButton = binding.btnReveal;
        revealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealCards();
            }
        });

        return root;
    }

    private void revealCards() {
        Random random = new Random();
        HashMap<String, Integer> cardFrequency = new HashMap<>();
        String mostRepeatedCard = null;
        int maxFrequency = 0;


        for (int i = 0; i < CARD_COUNT; i++) {
            int randomIndex = random.nextInt(imageResources.length);
            String card = imageResources[randomIndex];

            int resourceId = getResources().getIdentifier(card, "drawable", getActivity().getPackageName());
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resourceId);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 250)); // Small card size
            binding.cardLayout.addView(imageView);

            int currentFrequency = cardFrequency.getOrDefault(card, 0) + 1;
            cardFrequency.put(card, currentFrequency);

            if (currentFrequency > maxFrequency) {
                maxFrequency = currentFrequency;
                mostRepeatedCard = card;
            }
        }


        if (mostRepeatedCard != null) {
            int resourceId = getResources().getIdentifier(mostRepeatedCard, "drawable", getActivity().getPackageName());
            binding.highlightedCard.setImageResource(resourceId);
            binding.highlightedCard.setVisibility(View.VISIBLE);
            binding.title.setText("This is your faith for the next week");
        }
    }
}
