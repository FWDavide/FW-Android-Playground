package com.futureworkshops.android.architecture.domain.network.config;

/**
 * Network configuratino used for debugging & testing purposes.
 */

public class DebugNetworkConfig implements NetworkConfig {
    @Override
    public String getEndpoint() {
        return "www.someendpoint.com";
    }

    @Override
    public boolean useFakeRest() {
        return true;
    }
}
