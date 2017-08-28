package com.futureworkshops.android.architecture.presentation.login.dagger;


import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.presentation.login.LoginContract;
import com.futureworkshops.android.architecture.presentation.login.LoginInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginContract.View loginView;

    public LoginModule(LoginContract.View loginView) {
        this.loginView = loginView;
    }

    @Provides
    LoginInteractor providesLoginInteractor(RestManager restManager) {
        return new LoginInteractor(restManager);
    }

    @Provides
    LoginContract.View providesLoginView() {
        return loginView;
    }
}
