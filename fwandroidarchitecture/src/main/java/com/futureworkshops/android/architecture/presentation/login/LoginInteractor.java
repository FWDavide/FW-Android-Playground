package com.futureworkshops.android.architecture.presentation.login;

import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.model.User;

import io.reactivex.Single;

/**
 * Created by stelian on 28/08/2017.
 */

public class LoginInteractor {

    private final RestManager restManager;

    public LoginInteractor(RestManager restManager) {
        this.restManager = restManager;
    }

    public Single<User> login(@NonNull String username, @NonNull String password) {
        return restManager.login(username, password);
    }
}
