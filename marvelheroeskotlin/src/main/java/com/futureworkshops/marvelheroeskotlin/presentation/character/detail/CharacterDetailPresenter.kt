/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.presentation.character.detail

import com.futureworkshops.marvelheroeskotlin.presentation.common.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

class CharacterDetailPresenter
@Inject
constructor(characterDetailInteractor: CharacterDetailInteractor, compositeDisposable: CompositeDisposable) : BasePresenter<CharacterDetailView>(compositeDisposable)
