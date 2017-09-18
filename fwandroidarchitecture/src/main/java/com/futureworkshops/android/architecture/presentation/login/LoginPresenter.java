package com.futureworkshops.android.architecture.presentation.login;

import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.model.User;

import java.lang.ref.WeakReference;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by stelian on 28/08/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginInteractor mLoginInteractor;
    private final WeakReference<LoginContract.View> mViewWeakReference;

    public LoginPresenter(LoginInteractor loginInteractor, LoginContract.View view) {
        this.mLoginInteractor = loginInteractor;
        mViewWeakReference = new WeakReference<LoginContract.View>(view);
    }

    @Override
    public void login(@NonNull String username, @NonNull String password) {
        mLoginInteractor.login(username, password)
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        final LoginContract.View view = mViewWeakReference.get();
                        if(view!=null){
                            view.onLogin(user.getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        final LoginContract.View view = mViewWeakReference.get();
                        if(view!=null){
                            view.showServerError(e.getMessage());
                        }
                    }
                });
    }
}
