package com.futureworkshops.android.architecture.domain.dagger;

import android.app.Application;
import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.domain.dagger.module.ActivityComponentBindModule;
import com.futureworkshops.android.architecture.domain.dagger.module.ApplicationModule;
import com.futureworkshops.android.architecture.domain.dagger.module.NetModule;
import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;
import com.futureworkshops.android.architecture.presentation.FwAndroidArchitetureApp;
import com.futureworkshops.android.architecture.presentation.movies.dagger.MoviesActivityModule;

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
 * This is our top-level (top of the dependency graph) component and the only component we need to define.
 * <p>
 * Here we can find 2 new Modules that weren't used previous to Dagger v2.11.
 * <p>
 * {@link ActivityComponentBindModule}: Defines a mapping between Modules and Activities/Fragments who use them.
 * This way Dagger can generate subcomponents that will be added in the graph below their parent-component. In our
 * case, the AppComponent.
 * <p>
 * {@link AndroidInjectionModule}: is the module from Dagger.Android that helps with the generation
 * and location of subcomponents.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetModule.class,
        MoviesActivityModule.class,
        ActivityComponentBindModule.class,
        AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(FwAndroidArchitetureApp application);

    /**
     * This interface is used to provide parameters for modules.
     */
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        @BindsInstance
        AppComponent.Builder schedulerProvider(@NonNull SchedulersProvider schedulersProvider);

        @BindsInstance
        AppComponent.Builder restUrl(@NonNull String networkUrl);

        @BindsInstance
        AppComponent.Builder useFakeRest(boolean useFakeRest);

        AppComponent build();

    }

}
