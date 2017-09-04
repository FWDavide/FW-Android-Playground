package com.futureworkshops.android.architecture.domain.dagger.scope;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by dimitrios on 29/08/2017.
 *
 * Created a custom scope to showcase how scope is used.
 *
 * This is the scope we are going to use for the Login screen.
 *
 * A scope is used to limit the places where our Module can be used and help Dagger generate our Dependency Graph.
 * By using scopes you are reducing the chance of Cyclic dependencies in your graph.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginScope {

}
