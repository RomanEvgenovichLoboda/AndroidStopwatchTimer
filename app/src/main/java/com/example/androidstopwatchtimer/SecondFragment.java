package com.example.androidstopwatchtimer;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidstopwatchtimer.databinding.FragmentSecondBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    private Button strtButton;
    private Button stpButton;
    private TextView textStpWatch;
    private boolean isTimerRunning = false;
    private Timer timer;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textStpWatch=binding.textviewStpWatch;
        strtButton=binding.buttonStrt;
        stpButton=binding.buttonStop;
        stpButton.setEnabled(false);
        strtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isTimerRunning) startStopWatch();
            }
        });
        stpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTimerRunning) stopStopWatch();
            }
        });
        binding.buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void startStopWatch(){
        isTimerRunning = true;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            long seconds = 1000;
            @Override
            public void run() {
                updateTextTimer(seconds);
                seconds+=1000;
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);

        strtButton.setEnabled(false);
        stpButton.setEnabled(true);
    }
    private void stopStopWatch(){
        isTimerRunning = false;
        timer.cancel();
        timer = null;
        strtButton.setEnabled(true);
        stpButton.setEnabled(false);
        textStpWatch.setText("StopWatch");
    }
    private void updateTextTimer(long millisUntilFinished) {
        int hours = (int) (millisUntilFinished / (1000 * 60 * 60)) % 24;
        int minutes = (int) (millisUntilFinished / (1000 * 60)) % 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textStpWatch.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });
    }
}