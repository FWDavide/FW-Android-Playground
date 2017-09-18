package com.futureworkshops.android.architecture.domain.dagger.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by dimitrios on 22/08/2017.
 *
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 *
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you (via subcomponents)
 */
@Module
public abstract class ApplicationModule {

    /**
     * The @Binds annotation is... binding (duh!) the input of the function to the return type.
     * We use this to bind our Application class as a Context in the AppComponent,
     * so our Module can inject/provide Context
     */
    @Binds
    abstract Context bindContext(Application application);

}