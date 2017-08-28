package com.futureworkshops.android.architecture.domain.dagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.ds.drumcussacapp.guide.view.ContactActivity;
import com.ds.drumcussacapp.guide.view.EmergencyContactsActivity;
import com.ds.drumcussacapp.guide.view.FactBoxActivity;
import com.ds.drumcussacapp.internal.network.RestManager;
import com.ds.drumcussacapp.internal.rx.transformer.scheduler.SchedulersProvider;
import com.ds.drumcussacapp.placeholder.PlaceholderActivity;
import com.ds.drumcussacapp.refresh.RefreshTokenSyncAdapter;
import com.ds.drumcussacapp.state.requirement.Requirement;
import com.ds.drumcussacapp.tracking.model.TrackingEvent;
import com.jakewharton.rxrelay2.Relay;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context context();

    RestManager restManager();

    @Named(NamedDependency.DEFAULT_PREFERENCES)
    SharedPreferences defaultSharedPreferences();

    @Named(NamedDependency.FILTERS)
    SharedPreferences filterPreferences();

    Relay<Requirement> stateRelay();

    Relay<TrackingEvent> trackingRelay();

    Relay<Integer> sosRelay();

    SchedulersProvider schedulerProvider();

    void inject(PlaceholderActivity activity);

    void inject(EmergencyContactsActivity activity);

    void inject(ContactActivity activity);

    void inject(RefreshTokenSyncAdapter syncAdapter);

    void inject(FactBoxActivity factBoxActivity);
}
