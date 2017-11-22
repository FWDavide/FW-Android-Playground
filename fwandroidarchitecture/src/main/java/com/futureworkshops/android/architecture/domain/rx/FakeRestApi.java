package com.futureworkshops.android.architecture.domain.rx;

import com.futureworkshops.android.architecture.domain.network.RestApi;
import com.futureworkshops.android.architecture.model.User;

import java.util.Map;

import io.reactivex.Single;

public class FakeRestApi implements RestApi {

    @Override
    public Single<User> login(String contentType, Map<String, String> fieldMap) {
        return Single.just(user());
    }

    private User user() {
        return new User(" name", "email", "address");
    }
}
