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
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getModified() {
        return modified;
    }
    
    public void setModified(String modified) {
        this.modified = modified;
    }
    
    public String getResourceUri() {
        return resourceUri;
    }
    
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
    
    public ImageDto getThumbnail() {
        return thumbnail;
    }
    
    public void setThumbnail(ImageDto thumbnail) {
        this.thumbnail = thumbnail;
    }
}
