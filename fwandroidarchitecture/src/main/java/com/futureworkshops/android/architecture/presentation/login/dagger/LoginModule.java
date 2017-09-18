package com.futureworkshops.android.architecture.presentation.login.dagger;


import com.futureworkshops.android.architecture.domain.dagger.module.ApplicationModule;
import com.futureworkshops.android.architecture.domain.dagger.module.NetModule;
import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.presentation.login.LoginActivity;
import com.futureworkshops.android.architecture.presentation.login.LoginContract;
import com.futureworkshops.android.architecture.presentation.login.LoginInteractor;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * This is an example of a "screen-specific" module. Meaning a Module that provides dependencies that
 * are not already covered by our {@link ApplicationModule} and {@link NetModule}
 */
@Module
public abstract class LoginModule {

    /**
     * This method binds the LoginActivity to LoginContract.View. When the LoginContract.View is requested, the activity is returned.
     */
    @Binds
    abstract LoginContract.View providesContractView(LoginActivity activity);

    @Provides
    static LoginInteractor providesLoginInteractor(RestManager restManager) {
        return new LoginInteractor(restManager);
    }

    // FIXME :  inject presenter
}
