package com.example.stand310523.ui.LearnTarot;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.stand310523.databinding.FragmentLearntarotBinding;

public class LearnTarotFragment extends Fragment {

    private FragmentLearntarotBinding binding;
    private LearnTarotViewModel learntarotViewModel;
    private TextView imageText;
    private ImageView tarotImage;
    private ImageButton leftArrow, rightArrow;
    private int currentIndex = 0;


    private final String[] imageResources = new String[]{
            "thefool", "themagician", "thehighpriestess", "theempress", "theemperor",
            "thehierophant", "thelovers", "thechariot", "strength", "thehermit",
            "theweeloffotune", "justice", "thehangedman", "death", "temperance",
            "thedevilevil", "thetower", "thestar","themoon", "thesun", "judgement",
            "theworld",
    };

    private final String[] imageTexts = new String[]{
            "The Fool is young and vulnerable. He has not yet experienced the ups and downs of life, leaving him unaware of the magnitude of life's challenges, as well as the potential he holds. When The Fool comes up in a Tarot reading, you are encouraged to take on his open, willing energy and embrace all that lies ahead of you without worry.............................................................................",
            "The Magician card is a reminder that you are unique and have many gifts. These skills set you apart from the crowd and can help you begin new projects or overcome adversity. When The Magician comes up in your Tarot reading, it's a reminder that you already hold everything you need to move forward and accomplish what you've set out to do..............................................................................",
            "The most intuitive, connected card in the whole Tarot deck, The High Priestess is a card of awareness. This card urges you to listen to your inner voice and follow your instincts. Your mind knows far more than you think. When this card arises in your Tarot reading, stop looking for answers in the outside world. Turn within for the guidance you seek..............................................................................",
            "The Empress is the most feminine card in the Tarot, and greatly encourages compassion, beauty, and love. She is deeply connected to Mother Nature, and her influence is powerful when you absorb the energy of the natural world around you..............................................................................",
            "The Emperor is a card of leadership and power. He is an authoritative force who has been through many experiences to achieve this status. He represents structure and solidity, and reminds you that you, too, hold immense amounts of power over your own life, and what happens to it..............................................................................",
            "The Hierophant is like a messenger from the heavens. He is experienced in spirituality and guidance, and his job is to bring these lessons down to us here in the real world. When The Hierophant comes up in your Tarot reading, you're encouraged to follow the rules, and to find a spiritual perspective on your current situation..............................................................................",
            "The Lovers card represents the close relationships in your life. If it comes up in your Tarot reading, your love life is in need of extra attention. However, this is just as much a card about your values and decisions. You may find The Lovers come up when you are at a crossroads. Consider all the possible consequences of your choices..............................................................................",
            "The Chariot card is connected to your natural drive and determination, and can indicate an upcoming victory. This card reminds you that your greatest successes won't come through limited thinking -- when you combine the knowledge of your mind with that of your heart and spirit, you are an unstoppable force..............................................................................",
            "The Strength card represents courage, the fortitude of your heart, and your ability to withstand anything life hands you. If this card arises in your Tarot reading, you are reminded that you're strong enough to handle whatever you are facing and will come out of it with even more power than you had before..............................................................................",
            "The Hermit yearns to be alone. He knows that the only way to process what is happening in life is to withdraw from the noise of the world and create a quiet space of solitude. When The Hermit comes up in your reading, the answers you need will come from within. Be very still, and listen................................................................................",
            "The Wheel of Fortune is constantly revolving -- sometimes you will be at the top, and sometimes you will be at the bottom. This Tarot card reminds you that nothing is permanent, and, good or bad, you must cherish the lessons that this moment is bringing you..............................................................................",
            "Justice is your firm-but-fair reminder that there is a consequence for every action. Whatever life is handing you at this moment comes from decisions you and others have made in the past, reflecting natural cause-and-effect. When this card comes up in your Tarot reading, make sure you are acting fairly in all your interactions with others..............................................................................",
            "The Hanged Man tells you that sometimes small sacrifices are required to benefit the bigger picture. When The Hanged Man arises in your reading, you likely want to make a move but don't know where to begin. Lightening your grip on what's no longer working for you, or detaching from the outcome of your situation, can help you release yourself..............................................................................",
            "One of the most misunderstood Tarot cards, Death is not a card about physical death. The Death card speaks of cycles and is a reminder that all things must pass. Hanging on to situations from the past will hinder you from allowing new, better things to enter your life. In every ending lies a chance for a new beginning.............................................................................." ,
            "The Temperance card is a master of moderation. She encourages peace and patience, and reminds you to go with the flow of your life instead of trying to force its pace or direction. When Temperance turns up in your Tarot reading, it is a message to take things as they come, and remain flexible enough to change with the changes..............................................................................",
            "The Devil card carries themes of restraint and powerlessness. When it comes up in your Tarot reading, you are likely feeling stuck. The Devil has convinced you that you have no options, but this couldn't be further from the truth. You hold the keys to your own freedom, but it's up to you to open the lock..............................................................................",
            "The Tower represents destruction. It often comes up when everything in your life feels like its crumbling, and you have no way of stopping it. The message of this card is to just let it fall. The weakest parts of your life must be torn down in order to build something strong and sturdy in their place..............................................................................",
            "The Star is the embodiment of hope and healing. She is a calming influence that brings messages of renewal, optimism, and inspiration. When The Star comes up in your Tarot reading, she reminds you that the universe is working in your favor, and encourages you to have faith in where you are being taken..............................................................................",
            "The Moon card represents hidden thoughts, feelings, doubts and fears. When The Moon arises in your Tarot reading, you may be allowing your fears to override your faith in the future. Don't be deceived -- you can't believe everything that you see, hear, or think. If you can draw your feelings to the surface and address them, you can rid yourself of worry..............................................................................",
            "The Sun is a powerfully uplifting card, representing happiness, joy, vitality, and optimism. When The Sun comes up in your Tarot reading, it is an positive sign that things are working well for you and that you're moving in the right direction. Lift your head and realize all the good situations and people that are surrounding you now and always..............................................................................",
            "Judgement is a card where your past and your future come together. You are being called on to review your decisions and your actions until now, to ensure they are in-line with where you ultimately want to go. The Judgement card reminds you that your future is not set in stone, and that it's never too late to make a change for the better..............................................................................",
            "As the last card of the Major Arcana, The World represents completion and fulfillment. When it arises in your Tarot Reading, you are exactly where you're meant to be. You have a greater understanding of who you are after all you've been through, and you're ready for the next phase of your journey..............................................................................",

    };


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        learntarotViewModel = new ViewModelProvider(this).get(LearnTarotViewModel.class);
        binding = FragmentLearntarotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageText = binding.imageText;
        imageText.setMovementMethod(new ScrollingMovementMethod());
        imageText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        tarotImage = binding.tarotImage;
        leftArrow = binding.leftArrow;
        rightArrow = binding.rightArrow;

        updateImage();

        leftArrow.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateImage();
            }
        });

        rightArrow.setOnClickListener(v -> {
            if (currentIndex < imageResources.length - 1) {
                currentIndex++;
                updateImage();
            }
        });

        learntarotViewModel.getText().observe(getViewLifecycleOwner(), binding.textLearntarot::setText);
        return root;
    }

    private void updateImage() {
        int imageId = getResources().getIdentifier(imageResources[currentIndex], "drawable", getActivity().getPackageName());
        tarotImage.setImageResource(imageId);
        imageText.setText(imageTexts[currentIndex]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
