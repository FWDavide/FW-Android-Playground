/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.domain.navigator

import android.app.Activity
import android.content.Intent
import com.futureworkshops.marvelheroeskotlin.presentation.character.CharactersActivity
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class Navigator @Inject
constructor(private val baseActivity: Activity) {
    
    fun showCharactersScreen() {
        baseActivity.startActivity(Intent(baseActivity, CharactersActivity::class.java))
    }
}