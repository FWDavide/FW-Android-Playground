package com.futureworkshops.android.architecture.presentation.login;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by stelian on 28/08/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginInteractor loginInteractor;
    private final WeakReference<LoginContract.View> mViewWeakReference;

    public LoginPresenter(LoginInteractor loginInteractor, LoginContract.View view) {
        this.loginInteractor = loginInteractor;
        mViewWeakReference = new WeakReference<LoginContract.View>(view);
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
