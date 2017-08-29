package com.futureworkshops.android.architecture.domain.dagger;

import android.app.Application;

import com.futureworkshops.android.architecture.domain.dagger.module.ActivityComponentBindModule;
import com.futureworkshops.android.architecture.domain.dagger.module.AppModule;
import com.futureworkshops.android.architecture.domain.dagger.module.NetModule;
import com.futureworkshops.android.architecture.presentation.FwAndroidArchitetureApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by dimitrios on 22/08/2017.
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 *
 * This is a Dagger component. Refer to {@link FwAndroidArchitetureApp} for the list of Dagger components
 * used in this application.
 *
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link com.futureworkshops.android.architecture.presentation.FwAndroidArchitetureApp}.
 *
 * //{@link AndroidInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityComponentBindModule.class,
        AppModule.class,
        NetModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    void inject(FwAndroidArchitetureApp application);

}
