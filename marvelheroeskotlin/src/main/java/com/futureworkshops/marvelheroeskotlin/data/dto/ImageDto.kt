/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

data class ImageDto(@SerializedName("path") val path: String?,
                    @SerializedName("extension") val extension: String?)