package com.test.foursquaremultiple.di;


import com.test.foursquaremultiple.venuelist.VenueListActivity;
import com.test.foursquaremultiple.venuesearch.VenueSearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {

    @ContributesAndroidInjector(modules = FragmentSearchContributorModule.class)
    abstract VenueSearchActivity contributeVenueSearchActivity();

    @ContributesAndroidInjector(modules = FragmentListContributorModule.class)
    abstract VenueListActivity contributeVenueListActivity();


}
