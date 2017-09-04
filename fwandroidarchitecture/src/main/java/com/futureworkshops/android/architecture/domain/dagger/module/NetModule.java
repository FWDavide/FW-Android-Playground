package com.futureworkshops.android.architecture.domain.dagger.module;

import android.content.Context;

import com.futureworkshops.android.architecture.BuildConfig;
import com.futureworkshops.android.architecture.domain.network.RestApi;
import com.futureworkshops.android.architecture.domain.network.RestManager;
import com.futureworkshops.android.architecture.domain.rx.scheduler.WorkerSchedulerProvider;
import com.futureworkshops.android.architecture.presentation.util.NetworkUtil;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dimitrios on 22/08/2017.
 * <p>
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you (via subcomponents)
 */
@Module
public class NetModule {

    private final static String NAME_RETROFIT_DEFAULT = "NAME_RETROFIT_DEFAULT";

    private String mBaseUrl = "http://www.baseUrl.com";

    private static final int CACHE_SIZE = 50 * 1024 * 1024; //50 MB

    @Singleton
    @Named(NAME_RETROFIT_DEFAULT)
    @Provides
    Retrofit providesRetrofit(final Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .build();

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    RestApi providesApiService(@Named(NAME_RETROFIT_DEFAULT) Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }


    @Singleton
    @Provides
    RestManager providesRestManager(RestApi restApi) {
        return new RestManager(restApi, new WorkerSchedulerProvider());
    }

}
