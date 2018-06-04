/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.presentation.character.detail

import com.futureworkshops.marvelheroes.presentation.common.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by stelian on 09/04/2018.
 */

class CharacterDetailPresenter
@Inject
constructor(characterDetailInteractor: CharacterDetailInteractor, compositeDisposable: CompositeDisposable) : BasePresenter<CharacterDetailView>(compositeDisposable)
