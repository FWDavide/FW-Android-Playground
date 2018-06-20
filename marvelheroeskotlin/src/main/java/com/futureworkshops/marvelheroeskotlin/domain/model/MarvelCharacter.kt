/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.domain.model

import java.io.Serializable

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

data class MarvelCharacter(val id: String?,
                           val name: String?,
                           val description: String?,
                           val modified: String?,
                           val resourceUri: String?,
                           val thumbnailUrl: String?,
                           val imageUrl: String?,
                           val landscapeImageUrl: String?) : Serializable