/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.rx.scheduler;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler io();

    Scheduler ui();
}
