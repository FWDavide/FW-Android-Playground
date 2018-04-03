/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stelian on 03/04/2018.
 */

public class ImageDto {
    
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
    
    private static final String SEPARATOR = "/";
    private static final String DOT = ".";
    
    @SerializedName("path")
    private String path;
    
    @SerializedName("extension")
    private String extension;
    
    
    private String getPath() {
        return path;
    }
    
    private void setPath(String path) {
        this.path = path;
    }
    
    private String getExtension() {
        return extension;
    }
    
    private void setExtension(String extension) {
        this.extension = extension;
    }
    
    @Override
    public String toString() {
        return "ImageDto{" + "path='" + path + '\'' + ", extension='" + extension + '\'' + '}';
    }
}
