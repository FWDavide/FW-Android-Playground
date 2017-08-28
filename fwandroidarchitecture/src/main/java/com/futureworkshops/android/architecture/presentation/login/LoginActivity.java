package com.futureworkshops.android.architecture.presentation.login;

import android.os.Bundle;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

