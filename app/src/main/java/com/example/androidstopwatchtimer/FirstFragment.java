package com.example.androidstopwatchtimer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidstopwatchtimer.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    private NumberPicker pickerH;
    private NumberPicker pickerM;
    private NumberPicker pickerS;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //View view = inflater.inflate(R.layout.timer, container, false);
        pickerH = binding.HourPicker;
        pickerH.setMaxValue(23);
        pickerH.setMinValue(0);
        //pickerH.setDisplayedValues(setValueOfPickers(24));
        pickerM = binding.MinutesPicker;

        pickerM.setMaxValue(59);
        pickerM.setMinValue(0);
        //pickerM.setDisplayedValues(setValueOfPickers(60));
        pickerS = binding.SecondPicker;
        pickerS.setMaxValue(59);
        pickerS.setMinValue(0);
        //pickerS.setDisplayedValues(setValueOfPickers(60));

        binding.buttonStpWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}