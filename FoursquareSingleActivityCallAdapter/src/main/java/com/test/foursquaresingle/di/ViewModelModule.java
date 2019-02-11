package com.test.foursquaresingle.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.test.foursquaresingle.viewmodel.VenueDetailViewModel;
import com.test.foursquaresingle.viewmodel.VenueSearchViewModel;
import com.test.foursquaresingle.viewmodel.VenueViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VenueSearchViewModel.class)
    public abstract ViewModel bindVenueSearchViewModel(VenueSearchViewModel venueListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(VenueDetailViewModel.class)
    public abstract ViewModel bindVenueDetailViewModel(VenueDetailViewModel venueDetailViewModel);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(VenueViewModelFactory factory);
}
