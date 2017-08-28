package com.futureworkshops.android.architecture.domain.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.ds.drumcussacapp.BuildConfig;
import com.ds.drumcussacapp.common.StringUtils;
import com.ds.drumcussacapp.common.util.GsonUtil;
import com.ds.drumcussacapp.common.util.TimeUtil;
import com.ds.drumcussacapp.internal.network.OkHttpClientFactory;
import com.ds.drumcussacapp.internal.network.RestManager;
import com.ds.drumcussacapp.internal.network.RestService;
import com.ds.drumcussacapp.internal.network.token.UserPreferences;
import com.ds.drumcussacapp.internal.rx.transformer.scheduler.SchedulersProvider;
import com.ds.drumcussacapp.internal.rx.transformer.scheduler.WorkerSchedulerProvider;
import com.ds.drumcussacapp.state.requirement.Requirement;
import com.ds.drumcussacapp.tracking.model.TrackingEvent;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private static final String BASE_URL = BuildConfig.TARGET_API;
    public static final String FILTERS = "filters";

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context providesContext() {
        return context;
    }

    @Singleton
    @Provides
    RestManager providesRestManager(Retrofit retrofit,
                                    SchedulersProvider schedulerProvider,
                                    UserPreferences userPreferences) {
        return new RestManager(retrofit.create(RestService.class),
                schedulerProvider,
                userPreferences);
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(UserPreferences userPreferences) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClientFactory.okHttpClientBuilder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(getLoggingInterceptor())
                        .addNetworkInterceptor(new AuthInterceptor(userPreferences))
                        .build())
                .build();
    }

    @Singleton
    @Provides
    @Named(NamedDependency.DEFAULT_PREFERENCES)
    SharedPreferences providesDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    @Named(NamedDependency.FILTERS)
    SharedPreferences providesFilterPreferences() {
        return context.getSharedPreferences(FILTERS, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    SchedulersProvider providesSchedulerProvider() {
        return new WorkerSchedulerProvider();
    }

    @NonNull
    private HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        return interceptor;
    }

    private static class AuthInterceptor implements Interceptor {

        private final UserPreferences userPreferences;

        public AuthInterceptor(UserPreferences userPreferences) {
            this.userPreferences = userPreferences;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!StringUtils.isEmpty(userPreferences.getToken())) {
                String timestamp = TimeUtil.getFormattedCurrentTime();

                request = request.newBuilder()
                        .addHeader("Timestamp", timestamp)
                        .build();
                request = request.newBuilder()
                        .addHeader("Authorization", userPreferences.getType() + " " +
                                userPreferences.getToken())
                        .build();
            }

            return chain.proceed(request);
        }
    }
}