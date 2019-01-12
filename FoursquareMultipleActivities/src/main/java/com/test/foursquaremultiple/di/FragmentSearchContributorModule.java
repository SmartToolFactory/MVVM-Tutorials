
package com.test.foursquaremultiple.di;

import com.test.foursquaremultiple.venuesearch.VenueSearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * FragmentSearchContributorModule is used inside ActivityContributorModule
 * With @ContributesAndroidInjector(modules = MyFragmentModule.class)
 * defines which module will be used to inject objects to declared fragments
 */
@Module
public abstract class FragmentSearchContributorModule {

    @ContributesAndroidInjector
    abstract VenueSearchFragment contributeVenueSearchFragment();

}

