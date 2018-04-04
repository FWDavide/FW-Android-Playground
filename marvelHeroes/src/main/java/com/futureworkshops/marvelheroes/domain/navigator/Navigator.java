/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.navigator;

import android.content.Context;

import javax.inject.Inject;

/**
 * Class designed for delegating app navigation to the presenter level.
 *
 * Each presenter should receive an instance of this class(or a specialised version if required) and
 * use it to change screens.
 *
 * Created by stelian on 04/04/2018.
 */

public class Navigator {
    
    private Context context;
    
    @Inject
    public Navigator(Context context) {
        this.context = context;
    }
    
    public void showCharacterScreen() {
        // TODO: 04/04/2018 start characters activity
    }
}
