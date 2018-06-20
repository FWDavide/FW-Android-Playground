/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroeskotlin.domain.repository

import android.support.annotation.StringDef
import com.futureworkshops.marvelheroeskotlin.data.dto.ImageDto
import com.futureworkshops.marvelheroeskotlin.data.dto.MarvelCharacterDto
import com.futureworkshops.marvelheroeskotlin.domain.model.MarvelCharacter

/**
 * Created by dimitrios on 20/06/2018.
 * Copyright © 2018 - Future Workshops.
 * All rights reserved.
 */

object CharacterMapper {
    
    /** 50x75px  */
    const val PORTRAIT_SMALL = "portrait_small"
    
    /** 100x150px  */
    const val PORTRAIT_MEDIUM = "portrait_medium"
    
    /** 150x225px  */
    const val PORTRAIT_XLARGE = "portrait_xlarge"
    
    /** 168x252px  */
    const val PORTRAIT_FANTASTIC = "portrait_fantastic"
    
    /** 300x450px  */
    const val PORTRAIT_UNCANNY = "portrait_uncanny"
    
    /** 216x324px  */
    const val PORTRAIT_INCREDIBLE = "portrait_incredible"
    
    /** 65x45px  */
    const val STANDARD_SMALL = "standard_small"
    
    /** 100x100px  */
    const val STANDARD_MEDIUM = "standard_medium"
    
    /** 140x140px  */
    const val STANDARD_LARGE = "standard_large"
    
    /** 200x200px  */
    const val STANDARD_XLARGE = "standard_xlarge"
    
    /** 250x250px  */
    const val STANDARD_FANTASTIC = "standard_fantastic"
    
    /** 180x180px  */
    const val STANDARD_AMAZING = "standard_amazing"
    
    /** 120x90px  */
    const val LANDSCAPE_SMALL = "landscape_small"
    
    /** 175x130px  */
    const val LANDSCAPE_MEDIUM = "landscape_medium"
    
    /** 190x140px  */
    const val LANDSCAPE_LARGE = "landscape_large"
    
    /** 270x200px  */
    const val LANDSCAPE_XLARGE = "landscape_xlarge"
    
    /** 250x156px  */
    const val LANDSCAPE_AMAZING = "landscape_amazing"
    
    /** 464x261px  */
    const val LANDSCAPE_INCREDIBLE = "landscape_incredible"
    const val DETAIL = "detail"
    const val FULLSIZE = "fullsize"
    
    private const val SEPARATOR = "/"
    private const val DOT = "."
    
    
    @Retention(AnnotationRetention.RUNTIME)
    @StringDef(PORTRAIT_SMALL, PORTRAIT_MEDIUM, PORTRAIT_XLARGE, PORTRAIT_FANTASTIC, PORTRAIT_UNCANNY,
            PORTRAIT_INCREDIBLE, STANDARD_SMALL, STANDARD_MEDIUM, STANDARD_LARGE, STANDARD_XLARGE,
            STANDARD_FANTASTIC, STANDARD_AMAZING, LANDSCAPE_SMALL, LANDSCAPE_MEDIUM, LANDSCAPE_LARGE,
            LANDSCAPE_XLARGE, LANDSCAPE_AMAZING, LANDSCAPE_INCREDIBLE, DETAIL, FULLSIZE)
    annotation class ImageSize
    
    fun dtoToCharacter(dto: MarvelCharacterDto): MarvelCharacter {
        return MarvelCharacter(
                dto.id,
                dto.name,
                dto.description,
                dto.modified,
                dto.resourceUri,
                dto.thumbnail?.let { getThumbnailUrl(it) },
                dto.thumbnail?.let { getImageUrl(it) },
                dto.thumbnail?.let { getLandscapeImageUrl(it) }
        )
    }
    
    private fun getImageUrl(imageDto: ImageDto, @ImageSize size: String): String {
        return if (size == FULLSIZE) {
            imageDto.path + DOT + imageDto.extension
        } else {
            imageDto.path + SEPARATOR + size + DOT + imageDto.extension
        }
    }
    
    private fun getThumbnailUrl(imageDto: ImageDto): String {
        return getImageUrl(imageDto, PORTRAIT_UNCANNY)
    }
    
    private fun getImageUrl(imageDto: ImageDto): String {
        return getImageUrl(imageDto, FULLSIZE)
        
    }
    
    private fun getLandscapeImageUrl(imageDto: ImageDto): String {
        return getImageUrl(imageDto, LANDSCAPE_INCREDIBLE)
    }
    
    
}