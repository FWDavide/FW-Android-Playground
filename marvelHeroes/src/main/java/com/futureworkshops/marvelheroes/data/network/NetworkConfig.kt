/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network


import com.futureworkshops.marvelheroes.BuildConfig
import javax.inject.Inject

/**
 * Simple interface that defines parameters required to configure the network module.
 */

class NetworkConfig @Inject constructor() {
    
    /**
     * Return the endpoint the Rest client will connect to.
     */
    val endpoint: String = BuildConfig.MARVEL_HEROES_ENDPOINT
    
    
    /**
     * Get API access key;
     */
    val accessKey: String = BuildConfig.MARVEL_HEROES_CLIENT_ID
    
    
    /**
     * Get API access key;
     */
    val apiSecret: String = BuildConfig.MARVEL_HEROES_CLIENT_SECRET
    
}
