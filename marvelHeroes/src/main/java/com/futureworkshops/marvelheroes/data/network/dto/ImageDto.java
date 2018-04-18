/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stelian on 03/04/2018.
 */

public class ImageDto {
    
    @SerializedName("path")
    private String path;
    
    @SerializedName("extension")
    private String extension;
    
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getExtension() {
        return extension;
    }
    
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    @Override
    public String toString() {
        return "ImageDto{" + "path='" + path + '\'' + ", extension='" + extension + '\'' + '}';
    }
}
