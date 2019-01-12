package com.test.foursquaremultiple.di;

import com.test.foursquaremultiple.utils.AppExecutors;
import com.test.foursquaremultiple.api.FoursquareService;
import com.test.foursquaremultiple.repo.VenueRepository;
import com.test.foursquaremultiple.repo.remote.WebService;

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
        return new Retrofit.Builder()
                .baseUrl(FoursquareService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FoursquareService.class);
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
