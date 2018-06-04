/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.rx.scheduler

import io.reactivex.Scheduler

interface SchedulersProvider {
    
    fun io(): Scheduler
    
    fun ui(): Scheduler
}
