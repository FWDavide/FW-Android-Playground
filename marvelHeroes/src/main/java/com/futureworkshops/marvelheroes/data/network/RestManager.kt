/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network

import android.content.Context
import com.futureworkshops.marvelheroes.BuildConfig
import com.futureworkshops.marvelheroes.data.network.dto.ApiCollection
import com.futureworkshops.marvelheroes.data.network.dto.MarvelCharacterDto
import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider
import com.futureworkshops.marvelheroes.data.network.rx.transformers.SingleWorkerTransformer
import com.futureworkshops.marvelheroes.extensions.md5
import com.google.gson.Gson
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * Created by stelian on 03/04/2018.
 */

class RestManager(context: Context, private val schedulersProvider: SchedulersProvider,
                  networkConfig: NetworkConfig) {
    
    private val restApiService: RestApi
    
    /**
     * Add logs for DBEUG builds.
     * @return
     */
    private val httpLogginInterceptor: Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
    
    init {
        val client = OkHttpClient.Builder()
                .addInterceptor(getAuthInterceptor(context, networkConfig.accessKey, networkConfig.apiSecret))
                .addInterceptor(httpLogginInterceptor)
                .build()
        
        val retrofit = Retrofit.Builder()
                .baseUrl(networkConfig.endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        
        restApiService = retrofit.create(RestApi::class.java)
    }
    
    fun getCharactersWithQuery(characterQuery: Map<String, Any>): Single<List<MarvelCharacterDto>> {
        return restApiService.getCharacters(characterQuery)
                .flatMap<ApiCollection<List<MarvelCharacterDto>>> { response -> Single.just<ApiCollection<List<MarvelCharacterDto>>>(response.response!!) }  // extract ApiCollection<List<CharactersDto>>
                .flatMap { response -> Single.just<List<MarvelCharacterDto>>(response.response!!) }  // extract List<CharactersDto>
                .compose(SingleWorkerTransformer(schedulersProvider))
    }
    
    fun getCharacterDetails(characterId: String): Single<List<MarvelCharacterDto>> {
        return restApiService.getCharacter(characterId)
                .flatMap { response -> Single.just(response.response!!) }  // extract List<CharactersDto>
                .compose(SingleWorkerTransformer(schedulersProvider))
    }
    
    /**
     * Add authentication to every request.
     * @param context
     * @param apiKey
     * @param apiSecret
     * @return
     */
    private fun getAuthInterceptor(context: Context, apiKey: String, apiSecret: String): Interceptor {
        return AuthInterceptor(apiKey, apiSecret)
    }
}

private class AuthInterceptor(private val accessKey: String, private val secretKey: String) : Interceptor {
    
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        val timestamp = Calendar.getInstance().timeInMillis.toString()
        var hash: String? = null
        
        try {
            hash = generateHash(timestamp, accessKey, secretKey)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        // add API KEY as a URL parameter
        val url = request.url().newBuilder()
                .addQueryParameter(PARAM_TIMESTAMP_KEY, timestamp)
                .addQueryParameter(PARAM_API_KEY, accessKey)
                .addQueryParameter(PARAM_HASH_KEY, hash)
                .build()
        
        // create new request with updated URL
        val newRequest = request.newBuilder().url(url).build()
        
        return chain.proceed(newRequest)
    }
    
    @Throws(Exception::class)
    private fun generateHash(timestamp: String, publicKey: String, privateKey: String): String {
        try {
            return (timestamp + privateKey + publicKey).md5()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("cannot generate the api key", e)
        }
    }
    
    companion object {
        
        private const val PARAM_API_KEY = "apikey"
        private const val PARAM_TIMESTAMP_KEY = "ts"
        private const val PARAM_HASH_KEY = "hash"
    }
}
