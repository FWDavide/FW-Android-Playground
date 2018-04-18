/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.navigator;

import android.app.Activity;
import android.content.Intent;

import com.futureworkshops.marvelheroes.presentation.character.CharactersActivity;

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
    
    private Activity baseActivity;
    
    @Inject
    public Navigator(Activity activity) {
        this.baseActivity = activity;
    }
    
    public void showCharacterScreen() {
        baseActivity.startActivity(new Intent(baseActivity, CharactersActivity.class));
    }
}
