/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.data.network.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Simplified DTO for a Marvel character - this DTO ignores comics,events and stories;
 *
 * Created by stelian on 03/04/2018.
 */

public class CharacterDto {
    
    @SerializedName("id")
    private String id;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("modified")
    private String modified;
    
    @SerializedName("resourceURI")
    private String resourceUri;
    
    @SerializedName("thumbnail")
    private ImageDto thumbnail;
    
    private String getId() {
        return id;
    }
    
    private void setId(String id) {
        this.id = id;
    }
    
    private String getName() {
        return name;
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    private String getDescription() {
        return description;
    }
    
    private void setDescription(String description) {
        this.description = description;
    }
    
    private String getModified() {
        return modified;
    }
    
    private void setModified(String modified) {
        this.modified = modified;
    }
    
    private String getResourceUri() {
        return resourceUri;
    }
    
    private void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
    
    private ImageDto getThumbnail() {
        return thumbnail;
    }
    
    private void setThumbnail(ImageDto thumbnail) {
        this.thumbnail = thumbnail;
    }
}
