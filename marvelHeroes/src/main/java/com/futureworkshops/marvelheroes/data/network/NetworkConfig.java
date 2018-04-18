/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network;


import com.futureworkshops.marvelheroes.BuildConfig;

/**
 * Simple interface that defines parameters required to configure the network module.
 */

public final class NetworkConfig {
    
    /**
     * Return the endpoint the Rest client will connect to.
     */
    public String getEndpoint() {
        return BuildConfig.MARVEL_HEROES_ENDPOINT;
    }
    
    
    /**
     * Get API access key;
     */
    public String getAccessKey() {
        return BuildConfig.MARVEL_HEROES_CLIENT_ID;
    }
    
    
    /**
     */
    public String getApiSecret() {
        return BuildConfig.MARVEL_HEROES_CLIENT_SECRET;
    }
    
}
