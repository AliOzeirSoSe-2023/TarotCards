package com.example.stand310523.ui.MyReading;

import static com.example.stand310523.login.spiritualNametosend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.stand310523.DBHelperSaveAnswer;
import com.example.stand310523.R;
import com.example.stand310523.databinding.FragmentMyreadingBinding;

import java.util.ArrayList;

public class MyReadingFragment extends Fragment {

    private FragmentMyreadingBinding binding;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyReadingViewModel myReadingViewModel =
                new ViewModelProvider(this).get(MyReadingViewModel.class);

        binding = FragmentMyreadingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        DBHelperSaveAnswer dbHelper = new DBHelperSaveAnswer(getContext());
        ArrayList<String> readings = dbHelper.getReadings();

        //dbHelper.deleteAllReadings();


        listView = root.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, readings);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Reading")
                        .setMessage("Are you sure you want to delete this reading?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String reading = readings.get(position);
                                String[] parts = reading.split(":");
                                String idPart = parts[0];
                                if (idPart != null && !idPart.replaceAll("[^0-9]", "").isEmpty()) {
                                    int readingId = Integer.parseInt(idPart.replaceAll("[^0-9]", ""));
                                    dbHelper.deleteReading(readingId, spiritualNametosend);
                                    readings.remove(position);
                                    adapter.notifyDataSetChanged();
                                } else {

                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });


        final TextView textView = binding.textMyreading;
        myReadingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
