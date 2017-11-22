package com.futureworkshops.android.architecture.presentation;

import com.futureworkshops.android.architecture.domain.dagger.AppComponent;
import com.futureworkshops.android.architecture.domain.dagger.DaggerAppComponent;
import com.futureworkshops.android.architecture.domain.network.config.DebugNetworkConfig;
import com.futureworkshops.android.architecture.domain.rx.scheduler.WorkerSchedulerProvider;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by stelian on 28/08/2017.
 * <p>
 * extending {@link DaggerApplication} is the equivilent of implementing {@link dagger.android.HasActivityInjector} and
 * injecting a {@link dagger.android.DispatchingAndroidInjector}. What this means, is our Application will provide
 * {@link AndroidInjector} to our Activities.
 */
public class FwAndroidArchitetureApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * This method needs to be implemented (the implementation needs to build our top-level AppComponent) in
     * order to allow our Application class to provide AndroidInjectors.
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .networkConfiguration(new DebugNetworkConfig())
                .schedulerProvider(new WorkerSchedulerProvider())
                .build();
        appComponent.inject(this);
        return appComponent;
    }
}
