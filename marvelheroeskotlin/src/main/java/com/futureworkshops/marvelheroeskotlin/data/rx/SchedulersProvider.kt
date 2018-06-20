/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.rx

import io.reactivex.Scheduler

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

interface SchedulersProvider {
    
    fun io(): Scheduler
    
    fun ui(): Scheduler
    
}