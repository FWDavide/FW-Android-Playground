package com.futureworkshops.android.architecture.presentation.login;

import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.domain.network.RestManager;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by stelian on 28/08/2017.
 */

public class LoginInteractor {

    private final RestManager restManager;

    @Inject
    public LoginInteractor(RestManager restManager) {
        this.restManager = restManager;
    }

    public Completable login(@NonNull String username, @NonNull String password) {
        return restManager.login(username, password).toCompletable();
    }
}
