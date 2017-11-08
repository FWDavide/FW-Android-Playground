package com.futureworkshops.android.architecture.domain.dagger;

import android.app.Application;
import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.domain.dagger.module.ActivityComponentBindModule;
import com.futureworkshops.android.architecture.domain.dagger.module.ApplicationModule;
import com.futureworkshops.android.architecture.domain.dagger.module.FragmentComponentBindModule;
import com.futureworkshops.android.architecture.domain.dagger.module.NetModule;
import com.futureworkshops.android.architecture.domain.network.config.NetworkConfig;
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
        FragmentComponentBindModule.class,
        AndroidInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(FwAndroidArchitetureApp application);

    /**
     * This interface is used to provide parameters for modules.
     * <p> Every method annotated with {@link BindsInstance} will link the method return type
     * to the method input type for the entire dependency graph - this means that we can't have
     * 2 methods with {@link BindsInstance} that accept the same type of parameters !!</p>
     */
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        /**
         * Specify the {@link SchedulersProvider} to be used across the AppComponent graph.
         */
        @BindsInstance
        AppComponent.Builder schedulerProvider(@NonNull SchedulersProvider schedulersProvider);

        /**
         * Specify the {@link NetworkConfig} to be used across the AppComponent graph.
         */
        @BindsInstance
        AppComponent.Builder networkConfiguration(@NonNull NetworkConfig networkConfig);

        AppComponent build();
    }
}
