package com.futureworkshops.android.architecture.presentation.login.dagger;


import com.futureworkshops.android.architecture.domain.dagger.ActivityScope;
import com.futureworkshops.android.architecture.domain.dagger.AppComponent;
import com.futureworkshops.android.architecture.presentation.login.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
