package com.futureworkshops.android.architecture.domain.network;

import com.futureworkshops.android.architecture.model.User;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by stelian on 28/08/2017.
 */

public interface RestApi {

    @FormUrlEncoded
    @POST("token")
    Single<User> login(@Header("Content-Type") String contentType,
                       @FieldMap Map<String, String> fieldMap);
}
