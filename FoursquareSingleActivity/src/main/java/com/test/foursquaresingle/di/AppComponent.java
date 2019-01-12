package com.test.foursquaresingle.di;

import android.app.Application;

import com.test.foursquaresingle.application.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/*
 * ActivityContributorModule defines which Activities will have which modules and inject objects
 * If an Activity has any fragments it should add them via FragmentContributorModule with @ContributesAndroidInjector
 * @ContributesAndroidInjector(modules = {MainActivityModule.class, FragmentContributorModule.class})
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityContributorModule.class})

public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MyApplication app);
}
