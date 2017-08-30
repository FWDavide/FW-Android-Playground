package com.futureworkshops.android.architecture.presentation.login.dagger;


import android.app.Activity;

import com.futureworkshops.android.architecture.domain.dagger.module.ApplicationModule;
import com.futureworkshops.android.architecture.domain.dagger.module.NetModule;
import com.futureworkshops.android.architecture.domain.dagger.scope.ActivityScope;
import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.presentation.login.LoginActivity;
import com.futureworkshops.android.architecture.presentation.login.LoginContract;
import com.futureworkshops.android.architecture.presentation.login.LoginInteractor;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public abstract class LoginModule {

    @Binds
    abstract Activity bindActivity(LoginActivity activity);

    //This method binds the LoginActivity to LoginContract.View. When the LoginContract.View is requested, the activity is returned.
    @Binds
    abstract LoginContract.View bindMvpView(LoginActivity activity);

    @Provides
    static LoginInteractor providesLoginInteractor(RestManager restManager) {
        return new LoginInteractor(restManager);
    }

}
