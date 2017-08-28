package com.futureworkshops.android.architecture.presentation.login;

import android.support.annotation.NonNull;

/**
 * Contract between the View and the Presenter.
 */

public class LoginContract {

    public interface View {

        void onLogin();

        void showEmptyUsernameOrPassword();

        void showInvalidLoginInput();

        void showServerError();

    }

    public interface Presenter {

        void login(@NonNull String username, @NonNull String password);
    }
}
