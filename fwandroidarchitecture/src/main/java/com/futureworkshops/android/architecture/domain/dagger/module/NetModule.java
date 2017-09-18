package com.futureworkshops.android.architecture.domain.dagger.module;

import android.content.Context;

import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dimitrios on 22/08/2017.
 * <p>
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you (via subcomponents)
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    RestManager providesRestManager(Context context, SchedulersProvider schedulersProvider) {
        return new RestManager(context, schedulersProvider);
    }

}
