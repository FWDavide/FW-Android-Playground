/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network

import android.content.Context
import com.futureworkshops.marvelheroes.BuildConfig
import com.futureworkshops.marvelheroes.data.network.dto.ApiCollection
import com.futureworkshops.marvelheroes.data.network.dto.CharacterDto
import com.futureworkshops.marvelheroes.data.network.rx.scheduler.SchedulersProvider
import com.futureworkshops.marvelheroes.data.network.rx.transformers.SingleWorkerTransformer
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
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

/**
 * Created by stelian on 03/04/2018.
 */

class RestManager(context: Context, private val schedulersProvider: SchedulersProvider,
                  networkConfig: NetworkConfig) {
    
    private val restservice: RestApi
    
    /**
     * Add logs for DBEUG builds.
     * @return
     */
    private val httpLogginInterceptor: Interceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            return httpLoggingInterceptor
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
        
        restservice = retrofit.create(RestApi::class.java)
    }
    
    fun getCharactersWithQuery(characterQuery: Map<String, Any>): Single<List<CharacterDto>> {
        return restservice.getCharacters(characterQuery)
                .flatMap<ApiCollection<List<CharacterDto>>> { response -> Single.just<ApiCollection<List<CharacterDto>>>(response.response!!) }  // extract ApiCollection<List<CharactersDto>>
                .flatMap { response -> Single.just<List<CharacterDto>>(response.response!!) }  // extract List<CharactersDto>
                .compose(SingleWorkerTransformer(schedulersProvider))
    }
    
    fun getCharacterDetails(characterId: String): Single<List<CharacterDto>> {
        return restservice.getCharacter(characterId)
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
            val value = timestamp + privateKey + publicKey
            val md5Encoder = MessageDigest.getInstance("MD5")
            val md5Bytes = md5Encoder.digest(value.toByteArray())
            
            val md5 = StringBuilder()
            for (i in md5Bytes.indices) {
                md5.append(
                        Integer.toHexString(((md5Bytes[i] and 0xFF.toByte() or 0x100.toByte()).toInt()))
                        .substring(1, 3))
            }
            return md5.toString()
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
