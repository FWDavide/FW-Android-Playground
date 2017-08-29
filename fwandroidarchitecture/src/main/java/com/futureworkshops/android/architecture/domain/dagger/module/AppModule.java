package com.futureworkshops.android.architecture.domain.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by dimitrios on 22/08/2017.
 * <p>
 * Taken From https://github.com/googlesamples/android-architecture/blob/todo-mvp-dagger/
 * <p>
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 */
@Module
public abstract class AppModule {

    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);

    @Provides
    SharedPreferences providesSharedPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}