
package com.test.foursquaresingle.di;

import com.test.foursquaresingle.view.venuelist.VenueListFragment;
import com.test.foursquaresingle.view.venuesearch.VenueSearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * FragmentContributorModule is used inside ActivityContributorModule
 * With @ContributesAndroidInjector(modules = MyFragmentModule.class)
 * defines which module will be used to inject objects to declared fragments
 */
@Module
public abstract class FragmentContributorModule {

    @ContributesAndroidInjector
    abstract VenueSearchFragment contributeVenueSearchFragment();

    @ContributesAndroidInjector
    abstract VenueListFragment contributeVenueListFragment();
}

