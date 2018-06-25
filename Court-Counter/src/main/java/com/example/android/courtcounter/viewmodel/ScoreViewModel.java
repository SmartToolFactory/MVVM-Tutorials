package com.example.android.courtcounter.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class ScoreViewModel extends ViewModel {


    // Tracks the score for Team A
    public final ObservableField<Integer> scoreTeamA = new ObservableField<>();

    // Tracks the score for Team B
    public final ObservableField<Integer> scoreTeamB = new ObservableField<>();



    public void addScoreA(int score) {
        if (scoreTeamA.get() == null) {
            scoreTeamA.set(0);
        }
        scoreTeamA.set(scoreTeamA.get() + score);
    }

    public void addScoreB(int score) {
        if (scoreTeamB.get() == null) {
            scoreTeamB.set(0);
        }
        scoreTeamB.set(scoreTeamB.get() + score);
    }

    public void reset() {
        scoreTeamA.set(0);
        scoreTeamB.set(0);
    }
}
