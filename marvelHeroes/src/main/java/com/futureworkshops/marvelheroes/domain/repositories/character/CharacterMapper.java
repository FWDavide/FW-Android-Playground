/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.repositories.character;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import com.futureworkshops.marvelheroes.data.network.dto.CharacterDto;
import com.futureworkshops.marvelheroes.data.network.dto.ImageDto;
import com.futureworkshops.marvelheroes.domain.model.Character;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by stelian on 03/04/2018.
 */

public class CharacterMapper {
    
    /** 50x75px */
    public static final String PORTRAIT_SMALL = "portrait_small";
    
    /** 100x150px */
    public static final String PORTRAIT_MEDIUM = "portrait_medium";
    
    /** 150x225px */
    public static final String PORTRAIT_XLARGE = "portrait_xlarge";
    
    /** 168x252px */
    public static final String PORTRAIT_FANTASTIC = "portrait_fantastic";
    
    /** 300x450px */
    public static final String PORTRAIT_UNCANNY = "portrait_uncanny";
    
    /** 216x324px */
    public static final String PORTRAIT_INCREDIBLE = "portrait_incredible";
    
    /** 65x45px */
    public static final String STANDARD_SMALL = "standard_small";
    
    /** 100x100px */
    public static final String STANDARD_MEDIUM = "standard_medium";
    
    /** 140x140px */
    public static final String STANDARD_LARGE = "standard_large";
    
    /** 200x200px */
    public static final String STANDARD_XLARGE = "standard_xlarge";
    
    /** 250x250px */
    public static final String STANDARD_FANTASTIC = "standard_fantastic";
    
    /** 180x180px */
    public static final String STANDARD_AMAZING = "standard_amazing";
    
    /** 120x90px */
    public static final String LANDSCAPE_SMALL = "landscape_small";
    
    /** 175x130px */
    public static final String LANDSCAPE_MEDIUM = "landscape_medium";
    
    /** 190x140px */
    public static final String LANDSCAPE_LARGE = "landscape_large";
    
    /** 270x200px */
    public static final String LANDSCAPE_XLARGE = "landscape_xlarge";
    
    /** 250x156px */
    public static final String LANDSCAPE_AMAZING = "landscape_amazing";
    
    /** 464x261px */
    public static final String LANDSCAPE_INCREDIBLE = "landscape_incredible";
    public static final String DETAIL = "detail";
    public static final String FULLSIZE = "fullsize";
    
    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({PORTRAIT_SMALL, PORTRAIT_MEDIUM, PORTRAIT_XLARGE, PORTRAIT_FANTASTIC,
        PORTRAIT_UNCANNY, PORTRAIT_INCREDIBLE, STANDARD_SMALL, STANDARD_MEDIUM, STANDARD_LARGE,
        STANDARD_XLARGE, STANDARD_FANTASTIC, STANDARD_AMAZING, LANDSCAPE_SMALL, LANDSCAPE_MEDIUM,
        LANDSCAPE_LARGE, LANDSCAPE_XLARGE, LANDSCAPE_AMAZING, LANDSCAPE_INCREDIBLE, DETAIL, FULLSIZE})
    public @interface ImageSize {
    
    }
    
    private static final String SEPARATOR = "/";
    private static final String DOT = ".";
    
    public static Character dtoToCharacter(CharacterDto dto) {
        if (dto == null) {
            return null;
        }
        
        Character character = new Character();
        character.setId(dto.getId());
        character.setName(dto.getName());
        character.setDescription(dto.getDescription());
        character.setModified(dto.getModified());
        character.setResourceUri(dto.getResourceUri());
        character.setThumbnailUrl(getThumbnailUrl(dto.getThumbnail()));
        character.setImageUrl(getImageUrl(dto.getThumbnail()));
        character.setLandscapeImageUrl(getLandscapeImageUrl(dto.getThumbnail()));
        
        return character;
    }
    
    public static String getImageUrl(@NonNull ImageDto imageDto, @ImageSize String size) {
        if (size.equals(FULLSIZE)) {
            return imageDto.getPath() + DOT + imageDto.getExtension();
        } else {
            return imageDto.getPath() + SEPARATOR + size + DOT + imageDto.getExtension();
        }
    }
    
    private static String getThumbnailUrl(ImageDto imageDto) {
        return getImageUrl(imageDto, PORTRAIT_UNCANNY);
    }
    
    private static String getImageUrl(ImageDto imageDto) {
        return getImageUrl(imageDto, FULLSIZE);
        
    }
    
    private static String getLandscapeImageUrl(ImageDto imageDto) {
        return getImageUrl(imageDto, LANDSCAPE_INCREDIBLE);
    }
    
    
}
