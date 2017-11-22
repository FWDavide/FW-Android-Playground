package com.futureworkshops.android.architecture.domain.rx.scheduler;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler io();

    Scheduler ui();
}
