/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.network

import com.futureworkshops.marvelheroeskotlin.BuildConfig
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
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