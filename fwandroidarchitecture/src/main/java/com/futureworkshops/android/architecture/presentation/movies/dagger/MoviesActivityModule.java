package com.futureworkshops.android.architecture.presentation.movies.dagger;

import android.app.Activity;

import com.futureworkshops.android.architecture.presentation.movies.view.MoviesActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by dimitris on 15/09/2017.
 * <p>
 * This is an example of the alternative way of defining subcomponents... of defining the mapping of subcomponents
 * to our Dagger dependency graph manually.
 * <p>
 * As we always do when we want to inject dependencies in a new Activity/Fragment/Class... we define a Module. In our case {@link MoviesModule}
 * Then, as we saw in our {@link com.futureworkshops.android.architecture.presentation.login.dagger.LoginModule} example, we can go into {@link com.futureworkshops.android.architecture.domain.dagger.module.ActivityComponentBindModule}
 * and define a mapping between our Android Framework class (e.g. Activity) and our Module by using {@link dagger.android.ContributesAndroidInjector}. Then Dagger takes care of the rest for us and generates a subcomponent and adds it
 * to our graph below the top-level Component.
 * <p>
 * The only reason I can think of to NOT follow the above approach and manually define that mapping ourselves, as we do in this class, is to better understand how Dagger works... otherwise I can see no further advantage of this approach).
 * <p>
 * In this class, we define an Abstract module, that binds our {@link MoviesActivity} to our {@link MoviesSubcomponent}. By including this binding-module to the list of modules defined in our {@link com.futureworkshops.android.architecture.domain.dagger.AppComponent}
 * the subcomponent will be added to the dependency graph.
 */
@Module(subcomponents = MoviesSubcomponent.class)
public abstract class MoviesActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MoviesActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMoviesActivity(MoviesSubcomponent.Builder builder);

}
