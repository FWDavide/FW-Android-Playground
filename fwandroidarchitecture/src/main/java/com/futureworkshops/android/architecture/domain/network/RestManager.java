package com.futureworkshops.android.architecture.domain.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.futureworkshops.android.architecture.BuildConfig;
import com.futureworkshops.android.architecture.domain.rx.scheduler.SchedulersProvider;
import com.futureworkshops.android.architecture.domain.rx.transformers.SingleWorkerTransformer;
import com.futureworkshops.android.architecture.model.User;
import com.futureworkshops.android.architecture.presentation.util.NetworkUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * This class acts as an intermediate between user input and the actual network requests.
 * <p/>
 * All transformations to the input data should be done here in order to expose a simple to use API.
 */
public class RestManager {

    private static final String HEADER_CACHE_CONTROL = "Cache-Control";

    private String mBaseUrl = "http://www.baseUrl.com";

    private static final int CACHE_SIZE = 50 * 1024 * 1024; //50 MB
    private static final int CACHE_MAX_AGE = 60 * 60 * 24; //24 Hours

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private Retrofit mRetrofitSingleton;

    private final RestApi restService;
    private final SchedulersProvider schedulersProvider;

    public RestManager(@NonNull Context context,
                       @NonNull SchedulersProvider schedulersProvider) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getHttpLogginInterceptor())
                .addInterceptor(getOfflineCacheInterceptor(context))
                .addNetworkInterceptor(getNetworkCacheInterceptor())
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .build();

        mRetrofitSingleton = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.restService = mRetrofitSingleton.create(RestApi.class);
        this.schedulersProvider = schedulersProvider;
    }

    public Single<User> login(@NonNull String username, @NonNull String password) {
        Map<String, String> fieldMap = new HashMap<>();

        fieldMap.put(USERNAME, username);
        fieldMap.put(PASSWORD, password);

        return restService.login("application/x-www-form-urlencoded", fieldMap)
                .compose(new SingleWorkerTransformer<User>(schedulersProvider));
    }

    private Interceptor getHttpLogginInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    private Interceptor getNetworkCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(1, TimeUnit.DAYS)
                        .build();
                return response.newBuilder()
                        .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }


    public Interceptor getOfflineCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (NetworkUtil.isNetworkAvailable(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(1, TimeUnit.DAYS)
                            .build();
                    request = request.newBuilder().header(HEADER_CACHE_CONTROL, cacheControl.toString()).build();
                }
                return chain.proceed(request);
            }
        };
    }
}
