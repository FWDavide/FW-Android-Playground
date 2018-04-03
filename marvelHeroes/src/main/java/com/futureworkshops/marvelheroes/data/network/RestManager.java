/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.futureworkshops.marvelheroes.BuildConfig;
import com.futureworkshops.marvelheroes.domain.rx.scheduler.SchedulersProvider;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stelian on 03/04/2018.
 */

public class RestManager {
    
    private final RestApi restservice;
    private final SchedulersProvider schedulersProvider;
    
    public RestManager(@NonNull Context context, @NonNull SchedulersProvider schedulersProvider,
                       @NonNull NetworkConfig networkConfig) {
        
        this.schedulersProvider = schedulersProvider;
        
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(getAuthInterceptor(context, networkConfig.getAccessKey(), networkConfig.getApiSecret()))
            .addInterceptor(getHttpLogginInterceptor())
            .build();
        
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(networkConfig.getEndpoint())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        
        restservice = retrofit.create(RestApi.class);
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
    
    private Interceptor getAuthInterceptor(Context context, final String apiKey, final String apiSecret) {
        return new AuthInterceptor(apiKey, apiSecret);
    }
    
    private class AuthInterceptor implements Interceptor {
        
        private static final String PARAM_API_KEY = "apikey";
        private static final String PARAM_TIMESTAMP_KEY = "ts";
        private static final String PARAM_HASH_KEY = "hash";
        
        private final String accessKey;
        private final String secretKey;
        
        public AuthInterceptor(@NonNull String accessKey, @NonNull String secretKey) {
            this.accessKey = accessKey;
            this.secretKey = secretKey;
        }
        
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            
            final String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
            String hash = null;
            
            try {
                hash = generateHash(timestamp, accessKey, secretKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // add API KEY as a URL parameter
            HttpUrl url = request.url().newBuilder()
                .addQueryParameter(PARAM_TIMESTAMP_KEY, timestamp)
                .addQueryParameter(PARAM_API_KEY, accessKey)
                .addQueryParameter(PARAM_HASH_KEY, hash)
                .build();
            
            // create new request with updated URL
            Request newRequest = request.newBuilder().url(url).build();
            
            return chain.proceed(newRequest);
        }
        
        private String generateHash(String timestamp, String publicKey, String privateKey) throws Exception {
            try {
                String value = timestamp + privateKey + publicKey;
                MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
                byte[] md5Bytes = md5Encoder.digest(value.getBytes());
                
                StringBuilder md5 = new StringBuilder();
                for (int i = 0; i < md5Bytes.length; ++i) {
                    md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
                }
                return md5.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("cannot generate the api key", e);
                
            }
        }
    }
}
