package com.futureworkshops.android.architecture.presentation.fragments.dagger;


import com.futureworkshops.android.architecture.domain.dagger.module.ApplicationModule;
import com.futureworkshops.android.architecture.domain.dagger.module.NetModule;
import com.futureworkshops.android.architecture.presentation.fragments.TvShowsContract;
import com.futureworkshops.android.architecture.presentation.fragments.TvShowsInteractor;
import com.futureworkshops.android.architecture.presentation.fragments.TvShowsPresenter;
import com.futureworkshops.android.architecture.presentation.fragments.view.TvShowsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by David on 06/10/2017.
 */

/**
 * This is an example of a "screen-specific" module. Meaning a Module that provides dependencies that
 * are not already covered by our {@link ApplicationModule} and {@link NetModule}
 *
 * We annotate this Module as {@link} to limit the places it can be used.
 */
@Module()
public abstract class TvShowsModule {

    /**
     * This method binds the LoginActivity to LoginContract.View. When the LoginContract.View is requested, the activity is returned.
     */
    @Binds
    abstract TvShowsContract.View providesContractView(TvShowsFragment fragment);

    @Provides
    static TvShowsInteractor providesCreateProfileInteractor() {
        return new TvShowsInteractor();
    }

    @Provides
    static TvShowsPresenter providesCreateProfilePresenter(TvShowsInteractor interactor, TvShowsContract.View view){
        return new TvShowsPresenter(interactor,view);
    }

}
