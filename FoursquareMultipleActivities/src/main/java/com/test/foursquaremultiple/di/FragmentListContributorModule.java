
package com.test.foursquaremultiple.di;

import com.test.foursquaremultiple.venuelist.VenueListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * FragmentSearchContributorModule is used inside ActivityContributorModule
 * With @ContributesAndroidInjector(modules = MyFragmentModule.class)
 * defines which module will be used to inject objects to declared fragments
 */
@Module
public abstract class FragmentListContributorModule {

    @ContributesAndroidInjector
    abstract VenueListFragment contributeVenueListFragment();
}

