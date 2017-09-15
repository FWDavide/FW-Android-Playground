package com.futureworkshops.android.architecture.presentation.login;

import android.os.Bundle;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;

import dagger.android.AndroidInjection;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    /**
     * ALWAYS call AndroidInjection.inject(this) before ANYTHING else inside your Activity/Fragment.
     * {@link AndroidInjection} is the Dagger class responsible for injecting fields into Android Framework Components.
     * Previous to Dagger 2.11, our own defined components were used for injection. Now we got a class for it :)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onLogin() {

    }

    @Override
    public void showEmptyUsernameOrPassword() {

    }

    @Override
    public void showInvalidLoginInput() {

    }

    @Override
    public void showServerError() {

    }
}

