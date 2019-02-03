/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.tutorial2_5livedata_multiplefragments.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.test.tutorial2_5livedata_multiplefragments.R;
import com.test.tutorial2_5livedata_multiplefragments.viewmodel.SeekBarViewModel;

/**
 * Shows a SeekBar that is synced with a value in a ViewModel.
 */
public class FragmentB extends Fragment {

    private SeekBar mSeekBar;

    private SeekBarViewModel mSeekBarViewModel;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("FragmentB onAttach: " + this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("FragmentB onCreate: " + this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("FragmentB onCreateView: " + this);


        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment2, container, false);
        mSeekBar = root.findViewById(R.id.seekBar);

        mSeekBarViewModel = ViewModelProviders.of(getActivity()).get(SeekBarViewModel.class);

        subscribeSeekBar();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("FragmentB onViewCreated: " + this);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("FragmentB onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("FragmentB onResume()");
    }


    @Override
    public void onPause() {
        super.onPause();
        System.out.println("FragmentB onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("FragmentB onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("FragmentB onDestroyView: " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("FragmentB onDestroy: " + this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("FragmentB onDetach: " + this);
    }


    private void subscribeSeekBar() {

        // Update the ViewModel when the SeekBar is changed.
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mSeekBarViewModel.seekbarValue.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Update the SeekBar when the ViewModel is changed.
        mSeekBarViewModel.seekbarValue.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {

                System.out.println("FragmentB observe() lifeCycle: " + getActivity().getLifecycle().getCurrentState());

                if (value != null) {
                    mSeekBar.setProgress(value);
                }
            }
        });
    }
}
