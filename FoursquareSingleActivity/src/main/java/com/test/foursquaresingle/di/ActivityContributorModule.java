package com.test.foursquaresingle.di;



import com.test.foursquaresingle.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {

    @ContributesAndroidInjector(modules = FragmentContributorModule.class)
    abstract MainActivity contributeMainActivity();

}
