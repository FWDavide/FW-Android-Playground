package com.futureworkshops.android.architecture.presentation.login;

import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by stelian on 28/08/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginInteractor loginInteractor;

    public LoginPresenter(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void login(@NonNull String username, @NonNull String password) {
        loginInteractor.login(username, password).subscribe(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
