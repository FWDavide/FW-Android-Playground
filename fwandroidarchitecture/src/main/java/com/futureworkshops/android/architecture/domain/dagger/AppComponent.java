package com.futureworkshops.android.architecture.domain.dagger;

import android.app.Application;

import com.futureworkshops.android.architecture.domain.dagger.module.ActivityComponentBindModule;
import com.futureworkshops.android.architecture.domain.dagger.module.ApplicationModule;
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
 * <p>
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 * <p>
 * This is a Dagger component. Refer to {@link FwAndroidArchitetureApp} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link com.futureworkshops.android.architecture.presentation.FwAndroidArchitetureApp}.
 * <p>
 * //{@link AndroidInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetModule.class,
        ActivityComponentBindModule.class,
        AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(FwAndroidArchitetureApp application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();

    }

}
