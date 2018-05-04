/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.model


import java.io.Serializable

/**
 * Created by stelian on 03/04/2018.
 */

data class MarvelCharacter(val id: String?,
                           val name: String?,
                           val description: String?,
                           val modified: String?,
                           val resourceUri: String?,
                           val thumbnailUrl: String?,
                           val imageUrl: String?,
                           val landscapeImageUrl: String?) : Serializable
