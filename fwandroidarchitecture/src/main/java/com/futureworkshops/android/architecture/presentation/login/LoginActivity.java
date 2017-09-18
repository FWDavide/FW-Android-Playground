package com.futureworkshops.android.architecture.presentation.login;

import android.os.Bundle;
import android.widget.Toast;

import com.futureworkshops.android.architecture.R;
import com.futureworkshops.android.architecture.presentation.common.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginPresenter loginPresenter;

//    LoginPresenter loginPresenter =
//            new LoginPresenter(
//                    new LoginInteractor(
//                            new RestManager(new FakeRestApi(), new WorkerSchedulerProvider())));
//

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

        ButterKnife.bind(this);
    }

    @Override
    public void onLogin(String username) {
        Toast.makeText(this, "Logged in user : " + username, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyUsernameOrPassword() {

    }

    @Override
    public void showInvalidLoginInput() {

    }

    @Override
    public void showServerError(String message) {
        Toast.makeText(this, "Error on login : " + message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.email_sign_in_button)
    public void onLoginClicked() {
        loginPresenter.login("user", "pass");
    }
}

