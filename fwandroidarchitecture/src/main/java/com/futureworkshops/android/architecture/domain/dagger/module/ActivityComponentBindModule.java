package com.futureworkshops.android.architecture.domain.dagger.module;

import com.futureworkshops.android.architecture.presentation.login.LoginActivity;
import com.futureworkshops.android.architecture.presentation.login.dagger.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by dimitrios on 24/08/2017.
 *
 * This Module's purpose, is to define which Activity depends on which Module.
 * By providing this Module to our {@link com.futureworkshops.android.architecture.domain.dagger.AppComponent}
 * we are allowing Dagger to generate SubComponents and inject our activities.
 * The benefit of this approach, is we don't have to define Dagger Components for our Modules, with
 * the exception of our top-level AppComponent.
 * Subcomponents are components that live below the AppComponent in our graph.
 */
@Module
public abstract class ActivityComponentBindModule {

    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();

}
