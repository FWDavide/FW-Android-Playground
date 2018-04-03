/*
 * Copyright (c) 2018 FutureWorkshops. All rights reserved.
 */

package com.futureworkshops.marvelheroes.domain.model;


/**
 * Created by stelian on 03/04/2018.
 */

public class Character {
    
    private String id;
    
    private String name;
    
    private String description;
    
    private String modified;
    
    private String resourceUri;
    
    private String thumbnailUrl;
    
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
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
