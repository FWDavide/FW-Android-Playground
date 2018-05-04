/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.rx.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WorkerSchedulerProvider : SchedulersProvider {
    
    override fun io(): Scheduler {
        return Schedulers.io()
    }
    
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
