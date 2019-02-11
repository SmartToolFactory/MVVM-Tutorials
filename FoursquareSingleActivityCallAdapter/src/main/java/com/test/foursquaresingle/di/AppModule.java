package com.test.foursquaresingle.di;

import com.test.foursquaresingle.api.FoursquareService;
import com.test.foursquaresingle.api.LiveDataCallAdapterFactory;
import com.test.foursquaresingle.repo.VenueRepository;
import com.test.foursquaresingle.repo.remote.WebService;
import com.test.foursquaresingle.utils.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public abstract class AppModule {

    @Singleton
    @Provides
    static FoursquareService provideFoursquareService() {
        return new Retrofit
                .Builder()
                .baseUrl(FoursquareService.BASE_URL)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(FoursquareService.class);
    }

    @Singleton
    @Provides
    static VenueRepository provideVenueRepository(AppExecutors appExecutors, WebService webService) {
        return new VenueRepository(appExecutors, webService);
    }

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

}
