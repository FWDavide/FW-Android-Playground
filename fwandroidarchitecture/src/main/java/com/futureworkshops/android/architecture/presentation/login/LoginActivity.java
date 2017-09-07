package com.futureworkshops.android.architecture.presentation.login;

import android.os.Bundle;
import android.view.View;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.domain.rx.FakeRestApi;
import com.futureworkshops.android.architecture.domain.rx.scheduler.WorkerSchedulerProvider;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.futureworkshops.android.architecture.domain.network.RestManager.PASSWORD;
import static com.futureworkshops.android.architecture.domain.network.RestManager.USERNAME;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    LoginPresenter loginPresenter =
            new LoginPresenter(
                    new LoginInteractor(
                            new RestManager(new FakeRestApi(), new WorkerSchedulerProvider())));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void onLoginClicked() {
        loginPresenter.login(USERNAME, PASSWORD);
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

