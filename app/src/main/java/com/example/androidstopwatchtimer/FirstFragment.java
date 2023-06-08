package com.example.androidstopwatchtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

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
    private Button strtButton;
    private Button stpButton;
    private TextView textTimer;
    private boolean isTimerRunning = false;
    private CountDownTimer countDownTimer;
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
        textTimer=binding.textviewTimer;
        strtButton=binding.buttonStrt;
        stpButton=binding.buttonStop;
        stpButton.setEnabled(false);
        strtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isTimerRunning) {
                    int hours = pickerH.getValue();
                    int minutes = pickerM.getValue();
                    int seconds = pickerS.getValue();
                    long totalMilliseconds = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000);
                    if(totalMilliseconds>0){
                        countDownTimer = new CountDownTimer(totalMilliseconds, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                updateTextTimer(millisUntilFinished);
                            }

                            @Override
                            public void onFinish() {
                                textTimer.setText("Timer End");
                                resetTimer();
                            }
                        };

                        countDownTimer.start();
                        isTimerRunning = true;
                        strtButton.setEnabled(false);
                        stpButton.setEnabled(true);
                        pickerH.setEnabled(false);
                        pickerM.setEnabled(false);
                        pickerS.setEnabled(false);
                    }

                }
            }
        });
        stpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    countDownTimer.cancel();
                    textTimer.setText("Time Stop!");
                    resetTimer();
                }
            }
        });



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
    private void updateTextTimer(long millisUntilFinished) {
        int hours = (int) (millisUntilFinished / (1000 * 60 * 60)) % 24;
        int minutes = (int) (millisUntilFinished / (1000 * 60)) % 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;

        textTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    private void resetTimer() {
        isTimerRunning = false;
        strtButton.setEnabled(true);
        stpButton.setEnabled(false);
        pickerH.setEnabled(true);
        pickerM.setEnabled(true);
        pickerS.setEnabled(true);
        pickerH.setValue(0);
        pickerM.setValue(0);
        pickerS.setValue(0);
    }

}