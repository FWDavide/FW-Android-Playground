package com.futureworkshops.android.architecture.domain.network;

import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;
import com.futureworkshops.android.architecture.domain.rx.transformers.SingleWorkerTransformer;
import com.futureworkshops.android.architecture.model.User;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;


/**
 * This class acts as an intermediate between user input and the actual network requests.
 * <p/>
 * All transformations to the input data should be done here in order to expose a simple to use API.
 */
public class RestManager {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private final RestApi restService;
    private final SchedulersProvider schedulersProvider;

    public RestManager(@NonNull RestApi restService,
                       @NonNull SchedulersProvider schedulersProvider
                       ) {
        this.restService = restService;
        this.schedulersProvider = schedulersProvider;
    }

    public Single<User> login(@NonNull String username, @NonNull String password) {
        Map<String, String> fieldMap = new HashMap<>();

        fieldMap.put(USERNAME, username);
        fieldMap.put(PASSWORD, password);

        return restService.login("application/x-www-form-urlencoded", fieldMap)
//                .doOnSuccess(userPreferences::putLoginDetails)
                .compose(new SingleWorkerTransformer<User>(schedulersProvider));
    }


}
