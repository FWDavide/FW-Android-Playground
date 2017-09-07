package com.futureworkshops.android.architecture.domain.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;
import com.futureworkshops.android.architecture.domain.rx.transformers.SingleWorkerTransformer;
import com.futureworkshops.android.architecture.model.User;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * This class acts as an intermediate between user input and the actual network requests.
 * <p/>
 * All transformations to the input data should be done here in order to expose a simple to use API.
 */
public class RestManager {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private final RestApi restService;
    private final SchedulersProvider schedulersProvider;

    public RestManager(@NonNull RestApi restService,
                       @NonNull SchedulersProvider schedulersProvider) {
        this.restService = restService;
        this.schedulersProvider = schedulersProvider;
    }

    public Single<User> login(@NonNull String username, @NonNull String password) {
        Map<String, String> fieldMap = new HashMap<>();

        fieldMap.put(USERNAME, username);
        fieldMap.put(PASSWORD, password);

        return restService
                // this is an upstream method call relative to when the schedulers are applied
                .login("application/x-www-form-urlencoded", fieldMap)
                // this is an upstream method call relative to when the schedulers are applied
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        // this will execute on a worker scheduler
                        Log.e("Threading", "Before applying transformer " + Thread.currentThread().getName());
                        return user;
                    }
                })
                .doOnSuccess(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        // this will execute on a worker scheduler
                        Log.e("Threading", "Do on success before applying transformer " + Thread.currentThread().getName());
                    }
                })
                .compose(new SingleWorkerTransformer<User>(schedulersProvider))
                // this is an downstream method call relative to when the schedulers are applied
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        // this will execute on the main scheduler
                        Log.e("Threading", "After applying transformer " + Thread.currentThread().getName());
                        return user;
                    }
                })
                // this is an downstream method call relative to when the schedulers are applied
                .doOnSuccess(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        // this will execute on the main scheduler
                        Log.e("Threading", "Do on success after applying transformer " + Thread.currentThread().getName());
                    }
                });

    }
}
