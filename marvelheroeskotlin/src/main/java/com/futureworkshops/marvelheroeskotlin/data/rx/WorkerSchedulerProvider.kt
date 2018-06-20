/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class WorkerSchedulerProvider : SchedulersProvider {
    
    override fun io(): Scheduler {
        return Schedulers.io()
    }
    
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}