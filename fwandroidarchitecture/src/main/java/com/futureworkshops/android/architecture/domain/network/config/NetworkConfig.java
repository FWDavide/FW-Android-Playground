package com.futureworkshops.android.architecture.domain.network.config;

/**
 * Simple interface that defines parameters required to configure the network module.
 */

public interface NetworkConfig {

    /**
     * Return the endpoint the Rest client will connect to.
     */
    String getEndpoint();

    /**
     * Flag to determine if we need to use a fake REST implementation.
     */
    boolean useFakeRest();

}
