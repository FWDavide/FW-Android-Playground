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
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 *
 * This is our top-level (top of the dependency graph) component and the only component we need to define.
 *
 * Here we can find 2 new Modules that weren't used previous to Dagger v2.11.
 *
 * {@link ActivityComponentBindModule}: Defines a mapping between Modules and Activities/Fragments who use them.
 * This way Dagger can generate subcomponents that will be added in the graph below their parent-component. In our
 * case, the AppComponent.
 *
 * {@link AndroidInjectionModule}: is the module from Dagger.Android that helps with the generation
 * and location of subcomponents.
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
